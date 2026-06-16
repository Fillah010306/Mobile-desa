package com.example.fila_geometry.pertemuan6.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fila_geometry.data.AppDatabase
import com.example.fila_geometry.data.entity.AspirasiEntity
import com.example.fila_geometry.databinding.FragmentAspirasiBinding
import com.example.fila_geometry.pertemuan6.ui.AspirasiFormActivity
import kotlinx.coroutines.launch

class AspirasiFragment : Fragment() {
    private var _binding: FragmentAspirasiBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: AspirasiAdapter
    private lateinit var db: AppDatabase
    private val aspirasiList = mutableListOf<AspirasiEntity>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAspirasiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = AppDatabase.getInstance(requireContext())
        adapter = AspirasiAdapter(aspirasiList) { item ->
            deleteAspirasi(item)
        }

        binding.rvAspirasi.layoutManager = LinearLayoutManager(requireContext())
        binding.rvAspirasi.adapter = adapter

        fetchAspirasi()

        binding.fabAddAspirasi.setOnClickListener {
            startActivity(Intent(requireContext(), AspirasiFormActivity::class.java))
        }
    }

    private fun fetchAspirasi() {
        lifecycleScope.launch {
            val data = db.aspirasiDao().getAll()
            aspirasiList.clear()
            aspirasiList.addAll(data)
            adapter.notifyDataSetChanged()
        }
    }

    private fun deleteAspirasi(item: AspirasiEntity) {
        lifecycleScope.launch {
            db.aspirasiDao().delete(item)
            fetchAspirasi()
        }
    }

    override fun onResume() {
        super.onResume()
        fetchAspirasi()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
