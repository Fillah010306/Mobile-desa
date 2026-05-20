package com.example.fila_geometry.pertemuan10

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fila_geometry.databinding.ItemHouseBinding

class HouseAdapter(
    private val houseList: List<HouseModel>
) : RecyclerView.Adapter<HouseAdapter.HouseViewHolder>() {

    inner class HouseViewHolder(private val binding: ItemHouseBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(house: HouseModel) {
            binding.tvFamilyName.text = house.familyName
            binding.tvAddress.text = house.address
            
            // Menggabungkan list anggota keluarga menjadi string dengan nomor
            val membersText = house.members.mapIndexed { index, name ->
                "${index + 1}. $name"
            }.joinToString("\n")
            
            binding.tvMembers.text = membersText

            Glide.with(binding.root.context)
                .load(house.imageUrl)
                .centerCrop()
                .placeholder(android.R.drawable.ic_menu_report_image)
                .into(binding.imgHouseItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HouseViewHolder {
        val binding = ItemHouseBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return HouseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HouseViewHolder, position: Int) {
        holder.bind(houseList[position])
    }

    override fun getItemCount(): Int = houseList.size
}