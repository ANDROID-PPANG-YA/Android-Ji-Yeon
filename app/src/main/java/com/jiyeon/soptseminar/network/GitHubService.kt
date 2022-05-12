package com.jiyeon.soptseminar.network

import com.jiyeon.soptseminar.data.reponse.ResponseFollowers
import com.jiyeon.soptseminar.data.reponse.ResponseRepos
import retrofit2.Call
import retrofit2.http.GET

interface GitHubService{

    @GET("users/stopkite/following")
    fun getFollowers(): Call<List<ResponseFollowers>>

    @GET("users/stopkite/repos")
    fun getRepos(): Call<List<ResponseRepos>>
}

