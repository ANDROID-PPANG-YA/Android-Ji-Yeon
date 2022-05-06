package com.jiyeon.soptseminar.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.jiyeon.soptseminar.R
import com.jiyeon.soptseminar.data.RepoData
import com.jiyeon.soptseminar.databinding.FragmentRepoListBinding
import com.jiyeon.soptseminar.adapter.RepoAdapter
import com.jiyeon.soptseminar.util.BaseFragment
import com.jiyeon.soptseminar.util.ItemTouchHelperCallback


class RepoListFragment : BaseFragment<FragmentRepoListBinding>(R.layout.fragment_repo_list) {
    private lateinit var repoAdapter: RepoAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        itemEvent()
    }

    private fun initAdapter() {
        repoAdapter = RepoAdapter()
        binding.rvRepo.adapter = repoAdapter

        repoAdapter.repoList.addAll(
            listOf(
                RepoData(
                    "Android-Hyebin",
                    "\uD83E\uDD0D\uD83D\uDC7BT가 되고 싶은 F\uD83D\uDC7B\uD83E\uDD0D"
                ),
                RepoData("Android-Jaehun", "Android is my everything...★"),
                RepoData(
                    "Android-Jimin",
                    "♡ྉ안드는 ˗ˋˏ♡ˎˊ˗\uD835\uDC3B\uD835\uDC9C\uD835\uDCA9\uD835\uDC9C ˗ˋˏ♡ˎˊ˗ 야 d̶o̶o̶l̶이 될 수 없어—̳͟͞͞♡"
                ),
                RepoData("Android-JiYoung", "열공지영")
            )
        )

        repoAdapter.notifyDataSetChanged()
    }

    // 아이템 이벤트
    private fun itemEvent() {
        // 아이템 이동,삭제 이벤트
        val callback = ItemTouchHelperCallback(repoAdapter)
        val touchHelper = ItemTouchHelper(callback)

        touchHelper.attachToRecyclerView(binding.rvRepo)
        binding.rvRepo.adapter = repoAdapter

        repoAdapter.startDrag(object : RepoAdapter.OnStartDragListener {
            override fun onStartDrag(viewHolder: RecyclerView.ViewHolder) {
                touchHelper.startDrag(viewHolder)
            }
        })
    }
}