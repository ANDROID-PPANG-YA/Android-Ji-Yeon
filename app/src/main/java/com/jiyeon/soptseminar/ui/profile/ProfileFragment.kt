package com.jiyeon.soptseminar.ui.profile

import android.os.Bundle
import android.view.View
import com.jiyeon.soptseminar.R
import com.jiyeon.soptseminar.databinding.FragmentProfileBinding
import com.jiyeon.soptseminar.util.BaseFragment


class ProfileFragment : BaseFragment<FragmentProfileBinding>(R.layout.fragment_profile) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initTransFragmentEvent()
    }


    // Fragment 초기화
    private fun initTransFragmentEvent() {
        val followListFragment = ProfileFollowFragment()
        val repoListFragment = ProfileRepoFragment()

        childFragmentManager.beginTransaction().add(R.id.fragment_profile, followListFragment)
            .commit()

        binding.btnFollowerList.isSelected = true

        // 팔로우 rv 보여주기
        binding.btnFollowerList.setOnClickListener {
            childFragmentManager.beginTransaction().replace(R.id.fragment_profile, followListFragment).commit()
            binding.btnFollowerList.isSelected = true
            binding.btnRepoList.isSelected = false
        }

        // 레포 rv 보여주기
        binding.btnRepoList.setOnClickListener {
            childFragmentManager.beginTransaction().replace(R.id.fragment_profile, repoListFragment).commit()
            binding.btnRepoList.isSelected = true
            binding.btnFollowerList.isSelected = false
        }
    }

}