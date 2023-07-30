package com.example.firstaidmaster

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class FirstAidVideo : AppCompatActivity() {

    fun moveToVideo1Page(){
        val intent = Intent(this, Video1::class.java)
        startActivity(intent)
    }

    fun moveToVideo2Page(){
        val intent = Intent(this, Video2::class.java)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_aid_video)

        // 툴바 생성
        val toolbar: androidx.appcompat.widget.Toolbar
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)   // 뒤로가기 버튼 활성화 (화살표)
        supportActionBar?.setDisplayShowTitleEnabled(false) // 기본 apptitle 지우기

        //이미지뷰 버튼으로
        val imgVideo1 = findViewById<ImageView>(R.id.imageView1)
        val imgVideo2 = findViewById<ImageView>(R.id.imageView2)

        imgVideo1.setOnClickListener {
            moveToVideo1Page()
        }

        imgVideo2.setOnClickListener {
            moveToVideo2Page()
        }

    }
}