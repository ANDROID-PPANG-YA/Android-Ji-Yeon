package com.jiyeon.soptseminar

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

data class Data(
    val name:String,
    val email:String
)

data class ResponseSignIn(
    val status:Int,
    val message:String,
    val data:Data
)

data class RequestSignIn(
    @SerializedName("email")
    val id:String,
    val password:String
)

data class RegisterData(
    val id:Int
)

data class ResponseSignUp(
    val status:Int,
    val message:String,
    val data:RegisterData
)

data class RequestSignUp(
    @SerializedName("name")
    val name:String,
    val email:String,
    val password:String
)