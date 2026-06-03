package com.example.fila_geometry.pertemuan9.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.fila_geometry.R
import com.example.fila_geometry.databinding.FragmentNinthHomeBinding
import com.example.fila_geometry.pertemuan10.TenthActivity
import com.example.fila_geometry.Message.tutorial.TutorialActivity
import com.google.android.material.chip.Chip

class HomeFragment : Fragment() {
    private var _binding: FragmentNinthHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNinthHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref = requireActivity().getSharedPreferences("user_pref", android.content.Context.MODE_PRIVATE)
        val username = sharedPref.getString("username", "User")
        binding.tvUsernameHome.text = if (username.isNullOrBlank()) "Guest" else username

        setupChips()
        setupNavigation()
    }

    private fun setupNavigation() {
        binding.btnPertemuan10.setOnClickListener {
            val intent = Intent(requireContext(), TenthActivity::class.java)
            startActivity(intent)
        }
        binding.btnTutorial.setOnClickListener {
            val intent = Intent(requireContext(), TutorialActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupChips() {
        binding.chipGroupFilter.setOnCheckedStateChangeListener { group, checkedIds ->
            val selectedChipId = checkedIds.firstOrNull()
            if (selectedChipId != null) {
                val chip = group.findViewById<Chip>(selectedChipId)
                Toast.makeText(requireContext(), getString(R.string.ninth_filter_toast, chip.text), Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}