package com.jiyeon.soptseminar.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.jiyeon.soptseminar.R
import com.jiyeon.soptseminar.databinding.ActivityDetailBinding
import com.jiyeon.soptseminar.util.BaseActivity

class DetailActivity : BaseActivity<ActivityDetailBinding, DetailViewModel>(
    R.layout.activity_detail,
    DetailViewModel::class.java
) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 정보 받아오기
        initInfo()
    }

    override fun initViewModel() {
        super.initViewModel()
        binding.data =  viewModel
    }

    // 정보 초기화
    private fun initInfo() {
        viewModel.profile = intent.getIntExtra("profileData", 0)
        viewModel.name = intent.getStringExtra("nameData")!!
        viewModel.intro = intent.getStringExtra("infoData")!!

        binding.apply {
            Glide.with(this@DetailActivity).load(viewModel.profile).into(ivProfile)
            tvName.text = viewModel.name
            tvIntro.text = viewModel.intro
        }
    }
}