package com.jiyeon.soptseminar.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jiyeon.soptseminar.data.FollowerData
import com.jiyeon.soptseminar.databinding.ItemFollowListBinding

class FollowerAdapter(context: Context) : RecyclerView.Adapter<FollowerAdapter.FollowViewHolder>() {

    val myContext = context
    val followList = mutableListOf<FollowerData>()

    // 아이템 클릭이벤트
    interface OnItemClickListener{
        fun onItemClick(v: View, data:FollowerData, pos:Int)
    }

    private var listener : OnItemClickListener? = null

    fun setOnItemClickListener(listener : OnItemClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowViewHolder {
        val binding =
            ItemFollowListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FollowViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowViewHolder, position: Int) {
        holder.onBind(followList[position])
    }

    override fun getItemCount(): Int {
        return followList.size
    }


    inner class FollowViewHolder(
        private var binding: ItemFollowListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: FollowerData) {
            Glide.with(myContext).load(data.profile).circleCrop().into(binding.ivProfile)
            binding.tvName.text = data.name
            binding.tvIntroduce.text = data.intro

            // 아이템 클릭 이벤트 구현
            val pos = adapterPosition
            if(pos!=RecyclerView.NO_POSITION){
                itemView.setOnClickListener {
                    listener?.onItemClick(itemView,data,pos)
                }
            }
        }
    }
}