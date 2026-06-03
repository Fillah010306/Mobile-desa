package com.example.fila_geometry.pertemuan9.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.example.fila_geometry.R
import com.example.fila_geometry.Message.tutorial.TutorialActivity
import com.example.fila_geometry.databinding.FragmentMessageListBinding
import com.example.fila_geometry.pertemuan9.adapter.MessageAdapter
import com.example.fila_geometry.pertemuan9.model.MessageModel

class MessageListFragment : Fragment() {
    private var _binding: FragmentMessageListBinding? = null
    private val binding get() = _binding!!

    private val messageList = listOf(
        MessageModel("Ujang", "yok pubg!!?", "https://avatar.iran.liara.run/public/1"),
        MessageModel("Budi", "Mana kau?", "https://avatar.iran.liara.run/public/2"),
        MessageModel("Citra", "Jangan lupa tugasnya ya!", "https://avatar.iran.liara.run/public/3"),
        MessageModel("Dika", "Besok ngoding wo", "https://avatar.iran.liara.run/public/4"),
        MessageModel("Eka", "Nanti malam war clan", "https://avatar.iran.liara.run/public/5"),
        MessageModel("Fajar", "pubg gak?", "https://avatar.iran.liara.run/public/6"),
        MessageModel("Gita", "Pinjam duit?", "https://avatar.iran.liara.run/public/7"),
        MessageModel("Hana", "Lihat email woi", "https://avatar.iran.liara.run/public/8"),
        MessageModel("Irfan", "Oke noted", "https://avatar.iran.liara.run/public/9"),
        MessageModel("Joko", "Sampai jumpa di land of dawn", "https://avatar.iran.liara.run/public/10")
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMessageListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val adapter = MessageAdapter(requireContext(), messageList)
        binding.listViewMessages.adapter = adapter

        setupMenu()
    }

    private fun setupMenu() {
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.message_toolbar_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.action_tutorial -> {
                        val intent = Intent(requireContext(), TutorialActivity::class.java)
                        startActivity(intent)
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}