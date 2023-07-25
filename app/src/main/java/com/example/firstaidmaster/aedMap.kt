package com.example.firstaidmaster

import android.app.AlertDialog
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.naver.maps.map.LocationTrackingMode
import com.naver.maps.map.MapFragment
import com.naver.maps.map.MapView
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.util.FusedLocationSource

class aedMap : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var locationSource: FusedLocationSource    // 위치를 반환하는 구현체
    private lateinit var naverMap: NaverMap

    private val REQUEST_LOCATION =100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aed_map)
        locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)

        // 앱 실행시 권한이 부여되지 않은 경우 요청
        if (ActivityCompat.checkSelfPermission(this@aedMap, android.Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this@aedMap, android.Manifest.permission.ACCESS_COARSE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {

            var permissions = arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION,
                                        android.Manifest.permission.ACCESS_COARSE_LOCATION)

            ActivityCompat.requestPermissions(this@aedMap, permissions, REQUEST_LOCATION)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray) {
        if (locationSource.onRequestPermissionsResult(requestCode, permissions, grantResults)) {
            if (!locationSource.isActivated) {  // 권한 거부됨
                naverMap.locationTrackingMode = LocationTrackingMode.None
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

//        if (requestCode === REQUEST_LOCATION) {
//            if (grantResults.size > 0) {
//                for (grant in grantResults) {
//                    if (grant != PackageManager.PERMISSION_GRANTED) {
//                        System.exit(0)
//                    }
//                }
//            }
//        }
    }

    override fun onMapReady(naverMap: NaverMap) {
        this.naverMap = naverMap
        naverMap.locationSource = locationSource
        naverMap.locationTrackingMode = LocationTrackingMode.Follow
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1000
    }
}