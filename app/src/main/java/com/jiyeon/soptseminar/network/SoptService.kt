package com.jiyeon.soptseminar.network

import com.google.gson.annotations.SerializedName
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

// 로그인 Request
data class RequestSignIn(
    @SerializedName("email")
    val id:String,
    val password:String
)

// 로그인 Response
data class ResponseSignIn(
    val status:Int,
    val message:String,
    val data: Data
){
    data class Data(
        val name:String,
        val email:String
    )
}

// 회원가입 Request
data class RequestSignUp(
    val name:String,
    val email:String,
    val password:String
)

// 회원가입 Response
data class ResponseSignUp(
    val status:Int,
    val message:String,
    val data: Data
){
    data class Data(
        val id:Int
    )
}