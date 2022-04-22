package com.jiyeon.soptseminar.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jiyeon.soptseminar.data.RepoData
import com.jiyeon.soptseminar.databinding.ItemRepoListBinding
import com.jiyeon.soptseminar.util.ItemTouchHelperCallback
import java.util.*

class RepoAdapter() : RecyclerView.Adapter<RepoAdapter.RepoViewHolder>(), ItemTouchHelperCallback.OnItemMoveListener {

    val repoList = mutableListOf<RepoData>()

    private lateinit var dragListener: RepoAdapter.OnStartDragListener

    // 아이템 이동,삭제 이벤트
    interface OnStartDragListener {
        fun onStartDrag(viewHolder: RecyclerView.ViewHolder)
    }

    // 아이템 이동
    override fun onItemMoved(fromPos: Int, toPos: Int) {
        Collections.swap(repoList, fromPos, toPos)
        notifyItemMoved(fromPos, toPos)
    }

    // 아이템 삭제
    override fun onItemSwiped(pos: Int) {
        repoList.removeAt(pos)
        notifyItemRemoved(pos)
    }

    fun startDrag(listener: OnStartDragListener) {
        this.dragListener = listener
    }

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