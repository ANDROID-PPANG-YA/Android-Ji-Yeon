package com.jiyeon.soptseminar.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.jiyeon.soptseminar.R
import com.jiyeon.soptseminar.data.RepoData
import com.jiyeon.soptseminar.databinding.FragmentRepoListBinding
import com.jiyeon.soptseminar.ui.RepoAdapter


class RepoListFragment : Fragment() {
    private var _bining:FragmentRepoListBinding?=null
    private val binding get() = _bining!!

    private lateinit var repoAdapter:RepoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bining = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_repo_list,container,false)

        initAdapter()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _bining = null
    }

    private fun initAdapter(){
        repoAdapter = RepoAdapter()
        binding.rvRepo.adapter = repoAdapter

        repoAdapter.repoList.addAll(
            listOf(
                RepoData("Android-Hyebin","\uD83E\uDD0D\uD83D\uDC7BT가 되고 싶은 F\uD83D\uDC7B\uD83E\uDD0D"),
                RepoData("Android-Jaehun","Android is my everything...★"),
                RepoData("Android-Jimin","♡ྉ안드는 ˗ˋˏ♡ˎˊ˗\uD835\uDC3B\uD835\uDC9C\uD835\uDCA9\uD835\uDC9C ˗ˋˏ♡ˎˊ˗ 야 d̶o̶o̶l̶이 될 수 없어—̳͟͞͞♡"),
                RepoData("Android-JiYoung","열공지영")
            )
        )

        repoAdapter.notifyDataSetChanged()
    }
}