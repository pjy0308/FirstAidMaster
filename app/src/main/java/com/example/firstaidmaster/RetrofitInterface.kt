package com.example.firstaidmaster

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitInterface {
    @GET("getAedLcinfoInqire")
    fun getInfo(
        @Query("org") Org: String,
        @Query("buildAddress") BuildAddress: String,
        @Query("clerkTel") ClerkTel: String,
        @Query("buildPlace") BuildPlace: String
    ): Call<RetrofitClient>
}