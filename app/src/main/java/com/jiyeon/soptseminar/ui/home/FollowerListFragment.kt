package com.jiyeon.soptseminar.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.jiyeon.soptseminar.R
import com.jiyeon.soptseminar.data.FollowerData
import com.jiyeon.soptseminar.databinding.FragmentFollowListBinding
import com.jiyeon.soptseminar.ui.FollowerAdapter


class FollowerListFragment : Fragment() {

    private var _binding:FragmentFollowListBinding ?= null
    private val binding get() = _binding!!

    private lateinit var followerAdapter:FollowerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_follow_list, container, false)

        initAdapter()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // adapter 초기화
    private fun initAdapter(){
        followerAdapter = FollowerAdapter(requireContext())
        binding.rvFollow.adapter = followerAdapter
        followerAdapter.followList.addAll(
            listOf(
                FollowerData(R.drawable.profile_hyebin,"이혜빈","안드로이드 OB"),
                FollowerData(R.drawable.profile_jaehun,"조재훈","안드로이드 OB"),
                FollowerData(R.drawable.profile_jimin,"유지민","안드로이드 YB"),
                FollowerData(R.drawable.profile_jiyoung,"양지영","안드로이드 YB")
            )
        )

        followerAdapter.notifyDataSetChanged()
    }


}