package com.jiyeon.soptseminar.ui.home

import android.content.Intent
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

    private var _binding: FragmentFollowListBinding? = null
    private val binding get() = _binding!!

    private lateinit var followerAdapter: FollowerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_follow_list, container, false)

        initAdapter()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // adapter 초기화
    private fun initAdapter() {
        followerAdapter = FollowerAdapter(requireContext())
        binding.rvFollow.adapter = followerAdapter
        followerAdapter.followList.addAll(
            listOf(
                FollowerData(
                    R.drawable.profile_hyebin,
                    "이혜빈",
                    "안드로이드 OB 이혜빈입니다. 금잔디 5조 리더를 맡고 있습니다. 혜빈이한테 다 물어봐야징 헤헤헤헤헤헤헤"
                ),
                FollowerData(R.drawable.profile_jaehun, "조재훈", "안드로이드 OB 조재훈입니다."),
                FollowerData(R.drawable.profile_jimin, "유지민", "안드로이드 YB 유지민입니다."),
                FollowerData(R.drawable.profile_jiyoung, "양지영", "안드로이드 YB 양지영입니다.")
            )
        )

        followerAdapter.notifyDataSetChanged()

        // 상세보기 화면으로 이동하는 클릭 이벤트
        followerAdapter.setOnItemClickListener(object : FollowerAdapter.OnItemClickListener {
            override fun onItemClick(v: View, data: FollowerData, pos: Int) {
                val intent = Intent(requireContext(), DetailActivity::class.java)
                intent.putExtra("profileData", data.profile)
                    .putExtra("nameData", data.name)
                    .putExtra("infoData", data.intro)

                startActivity(intent)
            }

        })
    }


}