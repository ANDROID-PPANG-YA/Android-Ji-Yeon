package com.jiyeon.soptseminar.ui.profile

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.jiyeon.soptseminar.R
import com.jiyeon.soptseminar.adapter.ProfileFollowerAdapter
import com.jiyeon.soptseminar.data.ProfileFollowData
import com.jiyeon.soptseminar.data.reponse.ResponseFollowers
import com.jiyeon.soptseminar.databinding.FragmentProfileFollowBinding
import com.jiyeon.soptseminar.network.ServiceCreator
import com.jiyeon.soptseminar.util.BaseFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileFollowFragment : BaseFragment<FragmentProfileFollowBinding>(R.layout.fragment_profile_follow) {

    private lateinit var followAdapter:ProfileFollowerAdapter
    private val followList = mutableListOf<ProfileFollowData>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        followListNetWork()
        initAdapter()
    }

    private fun followListNetWork(){
        val call: Call<List<ResponseFollowers>> = ServiceCreator.gitHubService.getFollowers()
        call.enqueue(object : Callback<List<ResponseFollowers>> {
            override fun onResponse(
                call: Call<List<ResponseFollowers>>,
                response: Response<List<ResponseFollowers>>
            ) {
                if(response.isSuccessful){ // 통신 성공

                    val data = response.body()

                    for (i in 0 until data!!.size) {
                        followList.add(ProfileFollowData(data[i].avatar_url, data[i].github_id, "안녕하세요 ${data[i].github_id}입니다."))
                    }

                    initRv()
                }

            }

            override fun onFailure(call: Call<List<ResponseFollowers>>, t: Throwable) {
                Log.e("NetworkTest", "error:$t")
            }

        })

    }

    private fun initAdapter(){
        followAdapter = ProfileFollowerAdapter()
        binding.rvProfileFollow.adapter = followAdapter

        // Divider 추가
        val dividerItemDecoration = DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
        binding.rvProfileFollow.addItemDecoration(dividerItemDecoration)
    }

    private fun initRv(){
        followAdapter.followList = followList
        followAdapter.notifyDataSetChanged()
    }

}