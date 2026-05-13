package com.example.fila_geometry.pertemuan9.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.fila_geometry.R
import com.example.fila_geometry.databinding.FragmentHomeBinding
import com.google.android.material.chip.Chip

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Make workspace filter visible for Pertemuan 9
        binding.tvWorkspaceFilter.visibility = View.VISIBLE
        binding.chipGroupFilter.visibility = View.VISIBLE

        setupChips()
        setupMessageFragment()
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

    private fun setupMessageFragment() {
        childFragmentManager.beginTransaction()
            .replace(R.id.innerFragmentContainer, MessageListFragment())
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}