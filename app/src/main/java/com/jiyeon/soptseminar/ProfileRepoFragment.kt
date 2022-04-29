package com.jiyeon.soptseminar

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.jiyeon.soptseminar.adapter.ProfileRepoAdapter
import com.jiyeon.soptseminar.data.ProfileRepoData
import com.jiyeon.soptseminar.databinding.FragmentProfileRepoBinding
import com.jiyeon.soptseminar.util.BaseFragment

class ProfileRepoFragment : BaseFragment<FragmentProfileRepoBinding>(R.layout.fragment_profile_repo) {

    private lateinit var repoAdapter: ProfileRepoAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
    }


    private fun initAdapter(){
        repoAdapter = ProfileRepoAdapter()
        binding.rvRepo.adapter = repoAdapter
        repoAdapter.repoList.addAll(
            listOf(
                ProfileRepoData("안드로이드 과제 레포지토리1","안드로이드 파트 과제"),
                ProfileRepoData("안드로이드 과제 레포지토리2","안드로이드 파트 과제"),
                ProfileRepoData("안드로이드 과제 레포지토리3","안드로이드 파트 과제"),
                ProfileRepoData("안드로이드 과제 레포지토리4","안드로이드 파트 과제"),
            )
        )

        // Divider 추가
        val dividerItemDecoration = DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
        binding.rvRepo.addItemDecoration(dividerItemDecoration)

        repoAdapter.notifyDataSetChanged()
    }


}