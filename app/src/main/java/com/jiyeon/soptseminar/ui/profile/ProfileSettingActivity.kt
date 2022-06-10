package com.jiyeon.soptseminar.ui.profile

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jiyeon.soptseminar.databinding.ActivityProfileSettingBinding
import com.jiyeon.soptseminar.ui.signin.SignInActivity
import com.jiyeon.soptseminar.util.SOPTSharedPreferences

class ProfileSettingActivity:AppCompatActivity() {

    private lateinit var binding:ActivityProfileSettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProfileSettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 자동 로그인 해제
        initAutoLogout()
    }

    private fun initAutoLogout(){
        SOPTSharedPreferences.init(this)
        binding.tvLogout.setOnClickListener {
            SOPTSharedPreferences.setLogout(this)
            finishAffinity()
            startActivity(Intent(this,SignInActivity::class.java))
        }
    }
}