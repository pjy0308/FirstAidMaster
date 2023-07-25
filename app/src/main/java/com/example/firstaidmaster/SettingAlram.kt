package com.example.firstaidmaster

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat

class SettingAlram : AppCompatActivity() {
    private val REQUEST_NOTIFICATION = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting_alram)

        // 앱 실행시 권한이 부여되지 않은 경우 요청
        if (ActivityCompat.checkSelfPermission(this@SettingAlram, android.Manifest.permission.POST_NOTIFICATIONS)
            != PackageManager.PERMISSION_GRANTED) {

            var permissions = arrayOf(android.Manifest.permission.POST_NOTIFICATIONS)

            ActivityCompat.requestPermissions(this@SettingAlram, permissions, REQUEST_NOTIFICATION)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == REQUEST_NOTIFICATION) {
            if (grantResults.isEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
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