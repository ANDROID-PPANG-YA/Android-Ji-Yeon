package com.jiyeon.soptseminar.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.jiyeon.soptseminar.R
import com.jiyeon.soptseminar.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding:ActivityHomeBinding
    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        binding.viewModel = viewModel

        // 뷰 초기화
        initInfo()
        initTransFragmentEvent()
    }

    // 자기소개 초기화
    private fun initInfo(){
        binding.apply {
            Glide.with(this@HomeActivity).load(viewModel?.profile).into(ivProfile)
            tvName.text = viewModel?.name
            tvAge.text = viewModel?.age
            tvMbti.text = viewModel?.mbti
        }
    }

    // Fragment 초기화
    private fun initTransFragmentEvent(){
        val followListFragment = FollowerListFragment()
        val repoListFragment = RepoListFragment()

        supportFragmentManager.beginTransaction().add(R.id.fragment_home,followListFragment).commit()

        // 팔로우 rv 보여주기
        binding.btnFollowerList.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.fragment_home,followListFragment).commit()
        }

        // 레포 rv 보여주기
        binding.btnRepoList.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.fragment_home,repoListFragment).commit()
        }

    }

}