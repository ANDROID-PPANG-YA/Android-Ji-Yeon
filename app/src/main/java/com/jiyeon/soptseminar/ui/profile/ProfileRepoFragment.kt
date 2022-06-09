package com.jiyeon.soptseminar.ui.profile

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.jiyeon.soptseminar.R
import com.jiyeon.soptseminar.adapter.ProfileRepoAdapter
import com.jiyeon.soptseminar.data.ProfileRepoData
import com.jiyeon.soptseminar.data.reponse.ResponseRepos
import com.jiyeon.soptseminar.databinding.FragmentProfileRepoBinding
import com.jiyeon.soptseminar.network.ServiceCreator
import com.jiyeon.soptseminar.util.BaseFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileRepoFragment : BaseFragment<FragmentProfileRepoBinding>(R.layout.fragment_profile_repo) {

    private lateinit var repoAdapter: ProfileRepoAdapter
    private val repoList = mutableListOf<ProfileRepoData>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        repoListNetWork()
        initAdapter()
    }

    private fun repoListNetWork(){
        val call: Call<List<ResponseRepos>> = ServiceCreator.gitHubService.getRepos()
        call.enqueue(object : Callback<List<ResponseRepos>>{
            override fun onResponse(
                call: Call<List<ResponseRepos>>,
                response: Response<List<ResponseRepos>>
            ) {
                if(response.isSuccessful){
                    val data = response.body()

                    for (i in 0 until data!!.size){
                        repoList.add(ProfileRepoData(data[i].name,data[i].description))
                    }

                    initRv()
                }
            }

            override fun onFailure(call: Call<List<ResponseRepos>>, t: Throwable) {
                Log.e("NetworkTest", "error:$t")
            }

        })
    }

    private fun initAdapter(){
        repoAdapter = ProfileRepoAdapter()
        binding.rvRepo.adapter = repoAdapter

        // Divider 추가
        val dividerItemDecoration = DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
        binding.rvRepo.addItemDecoration(dividerItemDecoration)
    }

    private fun initRv(){
        repoAdapter.repoList = repoList
        repoAdapter.notifyDataSetChanged()
    }


}