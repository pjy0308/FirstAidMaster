package com.example.firstaidmaster

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView

class FirstAidList : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_aid_list)

        val listView = findViewById<ListView>(R.id.listView)
        val items = arrayOf("심폐소생술", "기도폐쇄", "골절", "화상", "열사병", "뱀에 물렸을 경우",
            "벌에 쏘였을 경우", "과호흡", "찔린상처", "부딪힌 상처(멍)", "찢어진 상처", "코피")

        val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(
            this, android.R.layout.simple_list_item_1, items
        )

        listView.adapter = arrayAdapter
    }
}