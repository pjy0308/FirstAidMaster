package com.example.firstaidmaster

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {
    lateinit var FirstAidEducation: Button
    lateinit var FirstAidVideo: Button
    lateinit var HowToUseAED: Button
    lateinit var imageButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FirstAidEducation = findViewById(R.id.FirtstAidEducation)
        FirstAidVideo = findViewById(R.id.FirstAidVideo)
        HowToUseAED = findViewById(R.id.HowToUseAED)
        imageButton = findViewById<ImageButton>(R.id.imageButton2)

        FirstAidEducation.setOnClickListener {
            val intent = Intent(this, FirstAidList::class.java)
            startActivity(intent)
        }

        HowToUseAED.setOnClickListener {
            val intent = Intent(this, aedInstruction::class.java)
            startActivity(intent)
        }

        imageButton.setOnClickListener {
            val intent = Intent(this, aedMap::class.java)
            startActivity(intent)
        }
    }
}