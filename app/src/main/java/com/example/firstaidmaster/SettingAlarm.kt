package com.example.firstaidmaster

import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Switch
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.firstaidmaster.databinding.ActivityMainBinding


class SettingAlarm : AppCompatActivity() {

    private val REQUEST_CODE_NOTIFICATION = 100

    lateinit var alarm_switch: Switch

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting_alarm)

        alarm_switch = findViewById(R.id.alarm_switch)

        // 툴바 생성
        val toolbar: androidx.appcompat.widget.Toolbar
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)   // 뒤로가기 버튼 활성화 (화살표)
        supportActionBar?.setDisplayShowTitleEnabled(false) // 기본 apptitle 지우기

        FirebaseMessagingService().getFirebaseToken()

        alarm_switch.isChecked = checkNotificationPermission()

        alarm_switch.setOnCheckedChangeListener { button, isChecked ->
            if (isChecked) {
                // 스위치 버튼이 on일 때 알림 권한 요청
                requestNotificationPermission()
                showToast("알림이 허용되었습니다. ")
            } else {
                cancelNotificationPermission()
                showToast("설정에서 알람 권한을 꺼주세요. ")
            }
        }
    }

    private fun checkNotificationPermission(): Boolean {
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        return notificationManager.areNotificationsEnabled()
    }

    private fun requestNotificationPermission() {
        if (ActivityCompat.checkSelfPermission(this@SettingAlarm, android.Manifest.permission.POST_NOTIFICATIONS)
            != PackageManager.PERMISSION_GRANTED) {

            var permissions = arrayOf(android.Manifest.permission.POST_NOTIFICATIONS)

            ActivityCompat.requestPermissions(this@SettingAlarm, permissions, REQUEST_CODE_NOTIFICATION)
        }
    }

    private fun cancelNotificationPermission() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri = Uri.fromParts("package", packageName, null)
        intent.data = uri
        startActivityForResult(intent, REQUEST_CODE_NOTIFICATION)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == REQUEST_CODE_NOTIFICATION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showToast("알림 권한이 허용되었습니다. ")
            } else {
                showToast("알림 권한이 거부되었습니다. ")
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}