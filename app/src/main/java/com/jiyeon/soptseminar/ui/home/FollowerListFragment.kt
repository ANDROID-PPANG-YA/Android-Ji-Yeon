package com.jiyeon.soptseminar.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.jiyeon.soptseminar.R
import com.jiyeon.soptseminar.data.FollowerData
import com.jiyeon.soptseminar.databinding.FragmentFollowListBinding
import com.jiyeon.soptseminar.adapter.FollowerAdapter
import com.jiyeon.soptseminar.util.BaseFragment
import com.jiyeon.soptseminar.util.ItemTouchHelperCallback
import com.jiyeon.soptseminar.util.VerticalSpaceItemDecoration
import kotlinx.coroutines.flow.combine


class FollowerListFragment : BaseFragment<FragmentFollowListBinding>(R.layout.fragment_follow_list) {

    private lateinit var followerAdapter: FollowerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        decoRVItem()
        itemEvent()
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
                FollowerData(R.drawable.profile_jiyoung, "양지영", "안드로이드 YB 양지영입니다."),
            )
        )


        followerAdapter.notifyDataSetChanged()
    }

    // 간격 넣기
    private fun decoRVItem(){
        val spaceDecoration = VerticalSpaceItemDecoration(50)
        binding.rvFollow.addItemDecoration(spaceDecoration)
    }

    // 아이템 이벤트
    private fun itemEvent(){
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

        // 아이템 이동,삭제 이벤트
        val callback = ItemTouchHelperCallback(followerAdapter)
        val touchHelper = ItemTouchHelper(callback)

        touchHelper.attachToRecyclerView(binding.rvFollow)
        binding.rvFollow.adapter = followerAdapter

        followerAdapter.startDrag(object : FollowerAdapter.OnStartDragListener {
            override fun onStartDrag(viewHolder: RecyclerView.ViewHolder) {
                touchHelper.startDrag(viewHolder)
            }
        })



    }

}