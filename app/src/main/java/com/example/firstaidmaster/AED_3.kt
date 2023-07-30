package com.example.firstaidmaster

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class AED_3 : AppCompatActivity() {
    lateinit var eduPage_next_btn: ImageView
    lateinit var eduPage_previous_btn: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aed3)

        val toolbar: androidx.appcompat.widget.Toolbar
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)   // 뒤로가기 버튼 활성화 (화살표)
        supportActionBar?.setDisplayShowTitleEnabled(false) // 기본 apptitle 지우기

        eduPage_previous_btn = findViewById(R.id.previous_btn)
        eduPage_next_btn = findViewById(R.id.next_btn)

        // 버튼 클릭 시 이전 페이지로 이동
        eduPage_previous_btn.setOnClickListener {
            val intent = Intent(this, AED_2::class.java)
            startActivity(intent)
        }

        // 버튼 클릭 시 다음 페이지로 이동
        eduPage_next_btn.setOnClickListener {
            val intent = Intent(this, AED_4::class.java)
            startActivity(intent)
        }
    }
}