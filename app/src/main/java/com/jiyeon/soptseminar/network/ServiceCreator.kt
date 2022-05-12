package com.jiyeon.soptseminar.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreator {
    private const val BASE_URL = "http://13.124.62.236/"
    private const val GITHUB_URL = "https://api.github.com/"

    private val soptRetrofit:Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val gitHubRetrofit:Retrofit = Retrofit.Builder()
        .baseUrl(GITHUB_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val soptService: SoptService = soptRetrofit.create(SoptService::class.java)
    val gitHubService:GitHubService = gitHubRetrofit.create(GitHubService::class.java)
}