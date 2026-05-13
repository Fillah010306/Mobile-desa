package com.example.fila_geometry.pertemuan9.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import com.example.fila_geometry.R
import com.example.fila_geometry.databinding.ItemMessageBinding
import com.example.fila_geometry.pertemuan9.model.MessageModel
import com.google.android.material.snackbar.Snackbar

class MessageAdapter(
    context: Context,
    private val messages: List<MessageModel>
) : ArrayAdapter<MessageModel>(context, 0, messages) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding: ItemMessageBinding = if (convertView == null) {
            ItemMessageBinding.inflate(LayoutInflater.from(context), parent, false)
        } else {
            ItemMessageBinding.bind(convertView)
        }
        
        val view = binding.root
        val message = messages[position]

        Glide.with(context)
            .load(message.avatarUrl)
            .placeholder(android.R.drawable.ic_menu_gallery)
            .into(binding.avatarImg)

        binding.textSender.text = message.senderName
        binding.textMessage.text = message.messageText

        view.setOnClickListener {
            Snackbar.make(
                parent,
                context.getString(R.string.ninth_message_snackbar, message.senderName, message.messageText),
                Snackbar.LENGTH_SHORT
            ).show()
        }

        return view
    }
}