package com.jiyeon.soptseminar.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jiyeon.soptseminar.data.ProfileFollowData
import com.jiyeon.soptseminar.databinding.ItemProfileFollowListBinding

class ProfileFollowerAdapter: RecyclerView.Adapter<ProfileFollowerAdapter.ProfileFollowerHolder>() {

    var followList= mutableListOf<ProfileFollowData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileFollowerHolder {
        val binding = ItemProfileFollowListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProfileFollowerHolder(binding)
    }

    override fun onBindViewHolder(holder: ProfileFollowerHolder, position: Int) {
        holder.onBind(followList[position])
    }

    override fun getItemCount(): Int = followList.size

    inner class ProfileFollowerHolder(private var binding:ItemProfileFollowListBinding):RecyclerView.ViewHolder(binding.root){
        fun onBind(data:ProfileFollowData){
            Glide.with(binding.root).load(data.profile_url).circleCrop().into(binding.ivProfile)
            binding.tvName.text = data.name
            binding.tvIntro.text = data.Intro
        }
    }
}