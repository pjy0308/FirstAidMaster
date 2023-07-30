package com.example.firstaidmaster

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class AED_5 : AppCompatActivity() {
    lateinit var eduPage_previous_btn: ImageView
    lateinit var finish_btn: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aed5)

        val toolbar: androidx.appcompat.widget.Toolbar
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)   // 뒤로가기 버튼 활성화 (화살표)
        supportActionBar?.setDisplayShowTitleEnabled(false) // 기본 apptitle 지우기

        eduPage_previous_btn = findViewById(R.id.previous_btn)
        finish_btn = findViewById(R.id.finish_btn)

        eduPage_previous_btn.setOnClickListener {
            val intent = Intent(this, AED_4::class.java)
            startActivity(intent)
        }

        // 완료 버튼 클릭 시 메인화면으로 이동
        finish_btn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}