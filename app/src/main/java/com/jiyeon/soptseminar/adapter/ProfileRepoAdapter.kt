package com.jiyeon.soptseminar.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jiyeon.soptseminar.data.ProfileRepoData
import com.jiyeon.soptseminar.databinding.ItemProfileRepoListBinding

class ProfileRepoAdapter: RecyclerView.Adapter<ProfileRepoAdapter.ProfileRepoHolder>() {

    var repoList= mutableListOf<ProfileRepoData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileRepoAdapter.ProfileRepoHolder {
        val binding = ItemProfileRepoListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProfileRepoHolder(binding)
    }

    override fun onBindViewHolder(holder: ProfileRepoAdapter.ProfileRepoHolder, position: Int) {
        holder.onBind(repoList[position])
    }

    override fun getItemCount(): Int = repoList.size

    inner class ProfileRepoHolder(private var binding: ItemProfileRepoListBinding):
        RecyclerView.ViewHolder(binding.root){
        fun onBind(data: ProfileRepoData){
            binding.tvName.text = data.name
            binding.tvInfo.text = data.info
        }
    }
}