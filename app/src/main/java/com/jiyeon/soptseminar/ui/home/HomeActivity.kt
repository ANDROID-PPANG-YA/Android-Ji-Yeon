package com.jiyeon.soptseminar.ui.home

import android.os.Bundle
import com.bumptech.glide.Glide
import com.jiyeon.soptseminar.R
import com.jiyeon.soptseminar.databinding.ActivityHomeBinding
import com.jiyeon.soptseminar.util.BaseActivity

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>(
    R.layout.activity_home,
    HomeViewModel::class.java
) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initInfo()
        initTransFragmentEvent()
    }

    // 뷰 모델 초기화
    override fun initViewModel() {
        super.initViewModel()
        binding.viewModel = viewModel
    }

    // 자기소개 초기화
    private fun initInfo() {
        binding.apply {
            Glide.with(this@HomeActivity).load(viewModel?.profile).into(ivProfile)
            tvName.text = viewModel?.name
            tvAge.text = viewModel?.age
            tvMbti.text = viewModel?.mbti
        }
    }

    // Fragment 초기화
    private fun initTransFragmentEvent() {
        val followListFragment = FollowerListFragment()
        val repoListFragment = RepoListFragment()

        supportFragmentManager.beginTransaction().add(R.id.fragment_home, followListFragment)
            .commit()

        // 팔로우 rv 보여주기
        binding.btnFollowerList.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_home, followListFragment).commit()
        }

        // 레포 rv 보여주기
        binding.btnRepoList.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.fragment_home, repoListFragment)
                .commit()
        }

    }
}