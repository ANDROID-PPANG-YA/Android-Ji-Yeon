package com.jiyeon.soptseminar.week4

import com.jiyeon.soptseminar.SoptService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreator {
    private const val BASE_URL = "http://13.124.62.236/"

    private val retrofit:Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val soptService:SoptService = retrofit.create(SoptService::class.java)
}