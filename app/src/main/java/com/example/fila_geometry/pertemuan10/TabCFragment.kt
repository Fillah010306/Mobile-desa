package com.example.fila_geometry.pertemuan10

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.fila_geometry.databinding.FragmentTabCBinding
import java.util.Locale

class TabCFragment : Fragment() {
    private var _binding: FragmentTabCBinding? = null
    private val binding get() = _binding!!

    // Generate 50 data rumah unik dengan 4 anggota keluarga per rumah
    private val houseList = List(50) { i ->
        val lastName = listOf("Setiawan", "Prasetyo", "Santoso", "Wibowo", "Hidayat")[i % 5]
        HouseModel(
            familyName = "Keluarga Bpk. ${listOf("Budi", "Andi", "Suryo", "Hasan", "Joko")[i % 5]} $lastName",
            address = "No. Rumah: ${String.format(Locale.US, "%02d", i + 1)}",
            members = listOf(
                "Ayah: ${listOf("Budi", "Andi", "Suryo", "Hasan", "Joko")[i % 5]} $lastName",
                "Ibu: ${listOf("Siti", "Ani", "Dewi", "Maya", "Rina")[i % 5]}",
                "Anak 1: ${listOf("Adit", "Bagas", "Candra", "Dika", "Ega")[i % 5]}",
                "Anak 2: ${listOf("Putri", "Rara", "Sari", "Tiara", "Vina")[i % 5]}"
            ),
            imageUrl = "https://picsum.photos/seed/house_unique_${i + 1}/800/600"
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTabCBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Load Header: Gambar Rumah Kosong / Utama sesuai permintaan
        Glide.with(this)
            .load("https://images.unsplash.com/photo-1512917774080-9991f1c4c750?q=80&w=800")
            .centerCrop()
            .into(binding.imgHouse)

        // Setup RecyclerView dengan HouseAdapter
        val adapter = HouseAdapter(houseList)
        binding.rvProducts.apply {
            layoutManager = LinearLayoutManager(requireContext())
            this.adapter = adapter
            isNestedScrollingEnabled = false // Agar scroll menyatu dengan NestedScrollView
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}