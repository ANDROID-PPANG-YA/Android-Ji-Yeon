package com.jiyeon.soptseminar.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jiyeon.soptseminar.data.RepoData
import com.jiyeon.soptseminar.databinding.ItemRepoListBinding

class RepoAdapter() : RecyclerView.Adapter<RepoAdapter.RepoViewHolder>() {

    val repoList = mutableListOf<RepoData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoAdapter.RepoViewHolder {
        val binding =
            ItemRepoListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.onBind(repoList[position])
    }

    override fun getItemCount(): Int {
        return repoList.size
    }

    class RepoViewHolder(
        private var binding: ItemRepoListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: RepoData) {
            binding.tvName.text = data.name
            binding.tvAbout.text = data.about
        }

    }

}