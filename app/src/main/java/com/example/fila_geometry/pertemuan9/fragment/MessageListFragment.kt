package com.example.fila_geometry.pertemuan9.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}