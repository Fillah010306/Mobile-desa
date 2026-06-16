package com.example.fila_geometry.pertemuan6.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fila_geometry.databinding.ItemOnboardingBinding

data class OnBoardingItem(
    val title: String,
    val description: String,
    val imageRes: Int
)

class OnBoardingAdapter(private val items: List<OnBoardingItem>) :
    RecyclerView.Adapter<OnBoardingAdapter.OnBoardingViewHolder>() {

    inner class OnBoardingViewHolder(val binding: ItemOnboardingBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        val binding = ItemOnboardingBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return OnBoardingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        val item = items[position]
        holder.binding.tvTitleOnboarding.text = item.title
        holder.binding.tvDescOnboarding.text = item.description
        holder.binding.ivOnboarding.setImageResource(item.imageRes)
    }

    override fun getItemCount(): Int = items.size
}
