package com.example.firstaidmaster

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    lateinit var FirstAidEducation: Button
    lateinit var FirstAidVideo: Button
    lateinit var HowToUseAED: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FirstAidEducation = findViewById(R.id.FirtstAidEducation)
        FirstAidVideo = findViewById(R.id.FirstAidVideo)
        HowToUseAED = findViewById(R.id.HowToUseAED)

        FirstAidEducation.setOnClickListener {
            val intent = Intent(this, FirstAidList::class.java)
            startActivity(intent)
        }

        HowToUseAED.setOnClickListener {
            val intent = Intent(this, aedInstruction::class.java)
            startActivity(intent)
        }
    }
}