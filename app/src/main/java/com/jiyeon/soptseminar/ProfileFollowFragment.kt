package com.jiyeon.soptseminar

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jiyeon.soptseminar.adapter.ProfileFollowerAdapter
import com.jiyeon.soptseminar.data.ProfileFollowData
import com.jiyeon.soptseminar.databinding.FragmentProfileFollowBinding
import com.jiyeon.soptseminar.util.BaseFragment


class ProfileFollowFragment : BaseFragment<FragmentProfileFollowBinding>(R.layout.fragment_profile_follow) {

    private lateinit var followAdapter:ProfileFollowerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
    }


    private fun initAdapter(){
        followAdapter = ProfileFollowerAdapter()
        binding.rvProfileFollow.adapter = followAdapter
        followAdapter.followList.addAll(
            listOf(
                ProfileFollowData(R.drawable.profile_hyebin, "이혜빈", "안드로이드 OB 이혜빈입니다."),
                ProfileFollowData(R.drawable.profile_jaehun, "조재훈", "안드로이드 OB 조재훈입니다."),
                ProfileFollowData(R.drawable.profile_jimin, "유지민", "안드로이드 YB 유지민입니다."),
                ProfileFollowData(R.drawable.profile_jiyoung, "양지영", "안드로이드 YB 양지영입니다."),
            )
        )

        // Divider 추가
        val dividerItemDecoration = DividerItemDecoration(requireContext(),LinearLayoutManager.VERTICAL)
        binding.rvProfileFollow.addItemDecoration(dividerItemDecoration)

        followAdapter.notifyDataSetChanged()
    }


}