package com.jiyeon.soptseminar.data.reponse

import com.google.gson.annotations.SerializedName

data class ResponseFollowers(
    @SerializedName("login") val github_id:String, // 깃허브 id
    val avatar_url:String, // 프로필 사진
)