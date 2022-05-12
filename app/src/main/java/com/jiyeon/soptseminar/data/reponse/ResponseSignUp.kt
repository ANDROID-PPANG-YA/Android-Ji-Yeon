package com.jiyeon.soptseminar.data.reponse

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