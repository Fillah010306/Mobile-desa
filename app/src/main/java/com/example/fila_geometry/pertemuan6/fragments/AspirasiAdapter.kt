package com.example.fila_geometry.pertemuan6.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fila_geometry.data.entity.AspirasiEntity
import com.example.fila_geometry.databinding.ItemAspirasiBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.text.SimpleDateFormat
import java.util.*

class AspirasiAdapter(
    private val list: List<AspirasiEntity>,
    private val onDelete: (AspirasiEntity) -> Unit
) : RecyclerView.Adapter<AspirasiAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemAspirasiBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemAspirasiBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.binding.tvSubjek.text = item.subjek
        holder.binding.tvPesan.text = item.pesan
        
        val sdf = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault())
        holder.binding.tvTanggal.text = sdf.format(Date(item.tanggal))

        holder.binding.btnDeleteAspirasi.setOnClickListener {
            MaterialAlertDialogBuilder(holder.itemView.context)
                .setTitle("Hapus Aspirasi")
                .setMessage("Apakah Anda yakin ingin menghapus aspirasi ini?")
                .setPositiveButton("Hapus") { _, _ -> onDelete(item) }
                .setNegativeButton("Batal", null)
                .show()
        }
    }

    override fun getItemCount(): Int = list.size
}
