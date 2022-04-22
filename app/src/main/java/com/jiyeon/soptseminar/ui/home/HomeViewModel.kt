package com.jiyeon.soptseminar.ui.home

import androidx.lifecycle.ViewModel
import com.jiyeon.soptseminar.R

class HomeViewModel : ViewModel() {
    var profile = R.drawable.profile
    var name = "이름: 정지연"
    var age = "나이: 25"
    var mbti = "MBTI: INFP"
    var introduce = "안녕하세요 안드로이드 파트 YB 정지연입니다. \n".repeat(50)
}