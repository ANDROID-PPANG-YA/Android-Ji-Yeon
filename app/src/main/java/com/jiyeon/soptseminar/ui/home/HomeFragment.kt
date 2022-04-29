package com.jiyeon.soptseminar.ui.home

import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.jiyeon.soptseminar.R
import com.jiyeon.soptseminar.adapter.HomeViewPagerAdapter
import com.jiyeon.soptseminar.databinding.FragmentHomeBinding
import com.jiyeon.soptseminar.util.BaseFragment


class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        initTabLayout()
    }

    // viewpager2 초기화
    private fun initAdapter(){
        val homePagerAdapter = HomeViewPagerAdapter(this)
        val fragmentList = listOf(FollowingFragment(),FollowerFragment())
        homePagerAdapter.fragments.addAll(fragmentList)

        binding.vpHome.adapter = homePagerAdapter

        binding.vpHome.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
            }
        })
    }

    // tablayout 초기화
    private fun initTabLayout(){
        TabLayoutMediator(binding.tblHome, binding.vpHome) { tab, position ->
            when(position){
                0 -> {tab.text = "팔로잉"}
                1 -> {tab.text = "팔로워"}
            }
        }.attach()
    }
}