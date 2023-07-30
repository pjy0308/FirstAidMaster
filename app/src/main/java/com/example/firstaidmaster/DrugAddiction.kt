package com.example.firstaidmaster

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class DrugAddiction : AppCompatActivity() {
    lateinit var finish_btn: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drug_addiction)

        val toolbar: androidx.appcompat.widget.Toolbar
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)   // 뒤로가기 버튼 활성화 (화살표)
        supportActionBar?.setDisplayShowTitleEnabled(false) // 기본 apptitle 지우기

        finish_btn = findViewById(R.id.finish_btn)

        // 완료 버튼 클릭 시 응급처치 목록으로 이동
        finish_btn.setOnClickListener {
            val intent = Intent(this, FirstAidList::class.java)
            startActivity(intent)
        }
    }
}