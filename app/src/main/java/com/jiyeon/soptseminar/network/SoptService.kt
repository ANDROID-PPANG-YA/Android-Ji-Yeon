package com.jiyeon.soptseminar.network

import com.jiyeon.soptseminar.data.reponse.ResponseSignIn
import com.jiyeon.soptseminar.data.reponse.ResponseSignUp
import com.jiyeon.soptseminar.data.request.RequestSignIn
import com.jiyeon.soptseminar.data.request.RequestSignUp
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SoptService{
    @POST("auth/signin")
    fun postLogin(
        @Body body: RequestSignIn
    ): Call<ResponseSignIn>

    @POST("auth/signup")
    fun postRegister(
        @Body body: RequestSignUp
    ):Call<ResponseSignUp>
}