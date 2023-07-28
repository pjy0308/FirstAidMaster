package com.example.firstaidmaster

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.widget.SwitchCompat
import androidx.core.app.ActivityCompat
import com.example.firstaidmaster.AlarmReceiver.Companion.ALARM_TIMER
import com.example.firstaidmaster.AlarmReceiver.Companion.NOTIFICATION_ID
import java.util.Calendar
import java.util.Date

class SettingAlarm : AppCompatActivity() {
    private val REQUEST_NOTIFICATION = 100
    private var alarmManager: AlarmManager? = null

    @SuppressLint("WrongViewCast", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting_alarm)

        // 툴바 생성
        val toolbar: androidx.appcompat.widget.Toolbar
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)   // 뒤로가기 버튼 활성화 (화살표)
        supportActionBar?.setDisplayShowTitleEnabled(false) // 기본 apptitle 지우기

        // 앱 실행시 권한이 부여되지 않은 경우 요청
        if (ActivityCompat.checkSelfPermission(this@SettingAlarm, android.Manifest.permission.POST_NOTIFICATIONS)
            != PackageManager.PERMISSION_GRANTED) {

            var permissions = arrayOf(android.Manifest.permission.POST_NOTIFICATIONS)

            ActivityCompat.requestPermissions(this@SettingAlarm, permissions, REQUEST_NOTIFICATION)
        }

        val switchButton: Switch = findViewById(R.id.alarm_switch)

        switchButton.setOnCheckedChangeListener { button, ischecked ->
            if (ischecked) {
                showToast("알림이 허용되었습니다. ")
            } else {
                showToast("알림이 거부되었습니다. ")
            }
        }

//        val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
//
//        val intent = Intent(this, AlarmReceiver::class.java)
//        val pendingIntent = PendingIntent.getBroadcast(
//            this, NOTIFICATION_ID, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        // 스위치 버튼
//        val switchButton: Switch = findViewById(R.id.alarm_switch)
//
//        switchButton.setOnCheckedChangeListener { button, ischecked ->
//            if (ischecked) {
//                val repeatInterval: Long = ALARM_TIMER * 1000L
//                val calendar = Calendar.getInstance().apply {
//                    timeInMillis = System.currentTimeMillis()
//                    set(Calendar.HOUR_OF_DAY, 7)
//                    set(Calendar.MINUTE, 30)
//                }
//
//                alarmManager.setRepeating(
//                    AlarmManager.RTC_WAKEUP,
//                    calendar.timeInMillis,
//                    repeatInterval,
//                    pendingIntent)
//            } else {
//                alarmManager.cancel(pendingIntent)
//            }
//        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == REQUEST_NOTIFICATION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showToast("알림 권한이 허용되었습니다. ")
            } else {
                showToast("알림 권한이 거부되었습니다. ")
            }
        }
    }

    private fun setAlarm(hour: Int, minute: Int) {
        // AlarmReceiver에 값 전달
        val intent = Intent(this, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            this,0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        // alram 등록 전, push cancel
        alarmManager?.cancel(pendingIntent)

        // 알람 시간 설정
        val calendar: Calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
            set(Calendar.SECOND, 0)
        }

        if (calendar.time < Date()) {
            calendar.add(Calendar.DAY_OF_MONTH, 1)
        }

        alarmManager?.setRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )
    }


    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}