package com.jiyeon.soptseminar.data.request

import com.google.gson.annotations.SerializedName

// 로그인 Request
data class RequestSignIn(
    @SerializedName("email")
    val id:String,
    val password:String
)