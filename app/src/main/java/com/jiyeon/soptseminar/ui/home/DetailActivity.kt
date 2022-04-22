package com.jiyeon.soptseminar.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.jiyeon.soptseminar.R
import com.jiyeon.soptseminar.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding:ActivityDetailBinding
    private lateinit var detailViewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        detailViewModel = ViewModelProvider(this)[DetailViewModel::class.java]
        binding.data = detailViewModel

        // 정보 받아오기
        initInfo()
    }

    // 정보 초기화
    private fun initInfo(){
        detailViewModel.profile = intent.getIntExtra("profileData",0)
        detailViewModel.name = intent.getStringExtra("nameData")!!
        detailViewModel.intro = intent.getStringExtra("infoData")!!

        binding.apply {
            Glide.with(this@DetailActivity).load(detailViewModel.profile).into(ivProfile)
            tvName.text = detailViewModel.name
            tvIntro.text = detailViewModel.intro
        }
    }
}