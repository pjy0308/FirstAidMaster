package com.example.firstaidmaster

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.VideoView
import android.widget.MediaController
import android.net.Uri

class Video2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video2)

        // 툴바 생성
        val toolbar: androidx.appcompat.widget.Toolbar
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)   // 뒤로가기 버튼 활성화 (화살표)
        supportActionBar?.setDisplayShowTitleEnabled(false) // 기본 apptitle 지우기

        val videoView1 = findViewById<VideoView>(R.id.videoView2)
        videoView1.setMediaController(MediaController(this))
        videoView1.setVideoURI(Uri.parse("android.resource://" + packageName + "/" + R.raw.heimlich_video))
        videoView1.start()
    }
}