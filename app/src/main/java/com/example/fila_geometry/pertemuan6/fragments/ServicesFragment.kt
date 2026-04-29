package com.example.fila_geometry.pertemuan6.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fila_geometry.databinding.FragmentServicesBinding
import com.example.fila_geometry.pertemuan6.ui.InputDataActivity

class ServicesFragment : Fragment() {
    private var _binding: FragmentServicesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentServicesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cardLayananKtp.setOnClickListener { openInputActivity("KTP Digital") }
        binding.cardLayananKk.setOnClickListener { openInputActivity("Kartu Keluarga") }
        binding.cardLayananAkta.setOnClickListener { openInputActivity("Akta Lahir") }
        binding.cardLayananPindah.setOnClickListener { openInputActivity("Pindah Domisili") }
    }

    private fun openInputActivity(serviceName: String) {
        val intent = Intent(requireContext(), InputDataActivity::class.java)
        intent.putExtra("SERVICE_TYPE", serviceName)
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}