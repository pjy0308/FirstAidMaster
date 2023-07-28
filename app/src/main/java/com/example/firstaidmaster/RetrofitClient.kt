package com.example.firstaidmaster

import android.content.ClipData
import com.google.android.gms.common.internal.Constants
import com.tickaroo.tikxml.TikXml
import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.Header

object RetrofitClient {
    fun getXMLInstance() : Retrofit {

        val BASE_URL = "http://apis.data.go.kr/B552657/AEDInfoInqireService/"

        val parser = TikXml.Builder().exceptionOnUnreadXml(false).build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(TikXmlConverterFactory.create(parser))
            .build()
    }

    @Xml(name = "response")
    data class AEDInfoInqireService(
        @Element(name = "body")
        val body: Body,
        @Element(name = "header")
        val header: Header
    )

    @Xml(name="header")
    data class Header(
        @PropertyElement(name="resultCode")
        val resultCode: Int,
        @PropertyElement(name="resultMsg")
        val resultMsg: String
    )

    @Xml(name = "body")
    data class Body(
        @Element(name="items")
        val items: Items,
        @PropertyElement(name="numOfRows")
        val numOfRows: Int,
        @PropertyElement(name="pageNo")
        val pageNo: Int,
        @PropertyElement(name="totalCount")
        val totalCount: Int
    )

    @Xml(name= "items")
    data class Items(
        @Element(name="item")
        val item: List<Items>
    )

    @Xml
    data class Item(
        @PropertyElement(name = "wgs84lon") // 경도
        var wgs84lon: Float,
        @PropertyElement(name = "wgs84lat") // 위도
        var wgs84lat: Float,
        @PropertyElement(name = "org")  // 설치기관명
        var org: String,
        @PropertyElement(name = "buildAddress") // 설치기관 주소
        var buildAddress: String,
        @PropertyElement(name="clerkTel")   // 설치기관 전화번호
        var clerkTel: String,
        @PropertyElement(name = "buildPlace")   // 설치위치
        var buildPlace: String,
    )
}