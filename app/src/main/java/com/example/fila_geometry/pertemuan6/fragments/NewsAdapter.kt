package com.example.fila_geometry.pertemuan6.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fila_geometry.data.model.NewsPost
import com.example.fila_geometry.databinding.ItemNewsBinding

class NewsAdapter(private val items: List<NewsPost>) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    inner class NewsViewHolder(val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val item = items[position]
        holder.binding.tvNewsTitle.text = item.title
        holder.binding.tvNewsDate.text = item.pubDate
        holder.binding.tvNewsDesc.text = item.description
        
        Glide.with(holder.itemView.context)
            .load(item.thumbnail)
            .into(holder.binding.ivNewsThumbnail)
    }

    override fun getItemCount(): Int = items.size
}
