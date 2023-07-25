package com.example.firstaidmaster

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    lateinit var FirstAidEducation: Button
    lateinit var FirstAidVideo: Button
    lateinit var HowToUseAED: Button
    lateinit var imageButton: ImageButton

    private val frame: RelativeLayout by lazy { // activity_main의 화면 부분
        findViewById(R.id.body_container)
    }
    private val bottomNagivationView: BottomNavigationView by lazy { // 하단 네비게이션 바
        findViewById(R.id.bottom_navigation)
    }

    fun moveToMapPage(){
        val intent = Intent(this, aedMap::class.java)
        startActivity(intent)
    }

    fun moveToHomePage(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

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

        // 애플리케이션 실행 후 첫 화면 설정
        //supportFragmentManager.beginTransaction().add(frame.id, HomeFragment()).commit()

        // 하단 네비게이션 바 클릭 이벤트 설정
        bottomNagivationView.setOnNavigationItemSelectedListener {item ->
            when(item.itemId) {
                R.id.home -> {
                    moveToHomePage()
                    true
                }
                R.id.map -> {
                    moveToMapPage()
                    true
                }
                R.id.setting -> {
                    replaceFragment(SettingFragment())
                    true
                }
                else -> false
            }
        }

    }
    // 화면 전환 구현 메소드
    fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(frame.id, fragment).commit()
    }
}