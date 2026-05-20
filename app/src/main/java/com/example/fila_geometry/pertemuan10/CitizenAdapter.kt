package com.example.fila_geometry.pertemuan10

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fila_geometry.databinding.ItemCitizenBinding

class CitizenAdapter(
    private val citizens: List<CitizenModel>
) : RecyclerView.Adapter<CitizenAdapter.CitizenViewHolder>() {

    inner class CitizenViewHolder(private val binding: ItemCitizenBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(citizen: CitizenModel) {
            binding.tvCitizenName.text = citizen.name
            binding.tvCitizenNik.text = "NIK: ${citizen.nik}"
            binding.tvRole.text = citizen.role

            Glide.with(binding.root.context)
                .load(citizen.imageUrl)
                .centerCrop()
                .placeholder(android.R.drawable.ic_menu_report_image)
                .into(binding.imgCitizen)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitizenViewHolder {
        val binding = ItemCitizenBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CitizenViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CitizenViewHolder, position: Int) {
        holder.bind(citizens[position])
    }

    override fun getItemCount(): Int = citizens.size
}