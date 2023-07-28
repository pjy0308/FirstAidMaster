package com.example.firstaidmaster

import android.content.Intent
import android.media.session.PlaybackState.CustomAction
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toolbar
import androidx.appcompat.app.ActionBar

class FirstAidList : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        var DataList: ArrayList<FirstAidName> = ArrayList<FirstAidName>()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_aid_list)

        // 툴바 생성
        val toolbar: androidx.appcompat.widget.Toolbar
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)   // 뒤로가기 버튼 활성화 (화살표)
        supportActionBar?.setDisplayShowTitleEnabled(false) // 기본 apptitle 지우기

        // 리스트에 들어갈 아이템 생성
        DataList.add(FirstAidName((R.drawable.dog_bite)))
        DataList.add(FirstAidName(R.drawable.fracture))
        DataList.add(FirstAidName(R.drawable.overbreathing))
        DataList.add(FirstAidName(R.drawable.heimrich))
        DataList.add(FirstAidName(R.drawable.snake_bite))
        DataList.add(FirstAidName(R.drawable.bee_bite))
        DataList.add(FirstAidName(R.drawable.cpr_name))
        DataList.add(FirstAidName(R.drawable.drug_addiction))
        DataList.add(FirstAidName(R.drawable.heatstroke))
        DataList.add(FirstAidName(R.drawable.burn))

        // FirstAidListAdapter와 연결
        var myListView = findViewById<ListView>(R.id.listView)
        myListView.adapter = FirstAidListAdapter(this, DataList)

        // 각 item 클릭시 화면 전환 이벤트
        myListView.setOnItemClickListener { FirstAidListAdapter, view, position, id ->
            if (position == 0) {
                // 첫 번째 응급처치 클릭
                val intent = Intent(this, CPR::class.java)
                startActivity(intent)
            } else if (position == 1) {
                // 두 번째 응급처치 클릭
            }
        }
    }
}