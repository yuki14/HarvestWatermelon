package com.example.game.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.core.db.Score
import com.example.game.databinding.RankingItemBinding

private object DiffCallback : DiffUtil.ItemCallback<Score>() {
    override fun areItemsTheSame(oldItem: Score, newItem: Score): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Score, newItem: Score): Boolean {
        return oldItem == newItem
    }

}

class RankingAdapter(
    private val viewLifecycleOwner: LifecycleOwner
    ) : ListAdapter<Score, RankingAdapter.RankingViewHolder>(DiffCallback) {

    class RankingViewHolder(private val binding: RankingItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Score, viewLifecycleOwner: LifecycleOwner) {
            binding.run {
                lifecycleOwner = viewLifecycleOwner
                ranking = item

                executePendingBindings()
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankingViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return RankingViewHolder(RankingItemBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: RankingViewHolder, position: Int) {
        holder.bind(getItem(position), viewLifecycleOwner)
    }

}