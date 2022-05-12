package com.jiyeon.soptseminar.data.request

// 회원가입 Request
data class RequestSignUp(
    val name:String,
    val email:String,
    val password:String
)