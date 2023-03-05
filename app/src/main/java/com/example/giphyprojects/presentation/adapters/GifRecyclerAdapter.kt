package com.example.giphyprojects.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.giphyprojects.databinding.GifItemBinding
import com.example.giphyprojects.model.pojo.Gif

class GifRecyclerAdapter(val navigateToItemInfo: (String) -> Unit) :
    PagingDataAdapter<Gif, GifRecyclerAdapter.GifViewHolder>(DiffCallback()) {

    private class DiffCallback : DiffUtil.ItemCallback<Gif>() {
        override fun areItemsTheSame(oldItem: Gif, newItem: Gif) = oldItem === newItem

        override fun areContentsTheSame(oldItem: Gif, newItem: Gif) = oldItem == newItem
    }

    inner class GifViewHolder(private val binding: GifItemBinding) : ViewHolder(binding.root) {

        fun bind(item: Gif) {
            binding.root.setOnClickListener {
                navigateToItemInfo.invoke(item.id)
            }
            Glide.with(binding.root)
                .asGif()
                .override(420, 320)
                .load(
                    item.mediaInformation?.original?.embedUrl ?: ""
                )
                .skipMemoryCache(false)
                .into(binding.ivGif)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifViewHolder {
        return GifViewHolder(
            GifItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: GifViewHolder, position: Int) {
        holder.bind(getItem(position) ?: Gif())
    }

}