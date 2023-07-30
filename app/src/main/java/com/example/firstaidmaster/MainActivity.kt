package com.example.firstaidmaster

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val frame: RelativeLayout by lazy { // activity_main의 화면 부분
        findViewById(R.id.body_container)
    }
    private val bottomNagivationView: BottomNavigationView by lazy { // 하단 네비게이션 바
        findViewById(R.id.bottom_navigation)
    }

    fun moveToMapPage(){
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.e-gen.or.kr/egen/search_aed.do"))
        startActivity(intent)
    }

    fun moveToHomePage(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun moveToSettingPage(){
        val intent = Intent(this, SettingAlarm::class.java)
        startActivity(intent)
    }

    fun moveToHowtoPage(){
        val intent = Intent(this, FirstAidList::class.java)
        startActivity(intent)
    }

    fun moveToHowtoAED() {
        val intent = Intent(this, AED_1::class.java)
        startActivity(intent)
    }

    fun moveToVideo() {
        val intent = Intent(this, FirstAidVideo::class.java)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
                    moveToSettingPage()
                    true
                }
                else -> false
            }
        }

        val imgWay = findViewById<ImageView>(R.id.imageButtonWay)
        val imgVideo = findViewById<ImageView>(R.id.imageButtonVideo)
        val imgAed = findViewById<ImageView>(R.id.imageButtonAED)

        imgWay.setOnClickListener {
            moveToHowtoPage()
        }

        imgAed.setOnClickListener {
            moveToHowtoAED()
        }

        imgVideo.setOnClickListener {
            moveToVideo()
        }

    }
    // 화면 전환 구현 메소드
    fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(frame.id, fragment).commit()
    }
}