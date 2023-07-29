package com.example.firstaidmaster


import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.Ringtone
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

private val TAG = "FirsebaseService"

class FirebaseMessagingService: FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        super.onNewToken(token)

        Log.d(TAG, "new Token: $token")

        val pref = this.getSharedPreferences("token", Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putString("token", token).apply()
        editor.commit()
        Log.i(TAG, "성공적으로 토큰을 저장함")
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        //super.onMessageReceived(message)

        Log.d(TAG, "From: " + remoteMessage!!.from)

        Log.d(TAG, "Message data : ${remoteMessage.data}")
        Log.d(TAG, "Message noti : ${remoteMessage.notification}")

        if (remoteMessage.data.isNotEmpty()) {
            sendNotification(remoteMessage)
        } else {
            Log.e(TAG, "data가 비어있습니다.메시지를 수신하지 못했습니다. ")
        }
    }

    private fun sendNotification(remoteMessage: RemoteMessage) {
        val unild: Int = (System.currentTimeMillis() / 7).toInt()

        val intent = Intent(this, SettingAlarm::class.java)
        for (key in remoteMessage.data.keys) {
            intent.putExtra(key, remoteMessage.data.getValue(key))
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        val pendingIntent = PendingIntent.getActivity(this, unild, intent,
                                PendingIntent.FLAG_UPDATE_CURRENT)

        val channelId = "my_channel"

        val soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // 오레오 버전 이후에는 채널 필요
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, "Notice", NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(channel)

            val notificationBuilder = NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.alram_icon_img)    // 아이콘 설정
                .setContentTitle(remoteMessage.data["title"].toString())    // 제목
                .setContentText(remoteMessage.data["body"].toString())  // 메시지 내용
                .setAutoCancel(true)    // 알림 클릭 시 삭제
                .setSound(soundUri) // 알림 소리
                .setContentIntent(pendingIntent)    // 알림 실행 시 intent

            notificationManager.notify(unild, notificationBuilder.build())
        }


    }

    fun getFirebaseToken() {
        FirebaseMessaging.getInstance().token.addOnSuccessListener {
            Log.d(TAG, "token=${it}")
        }
    }
}