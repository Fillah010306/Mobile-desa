package com.example.fila_geometry.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.fila_geometry.pertemuan6.ui.MainActivity

class ReminderReceiverReminderReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        
        val title = intent.getStringExtra("title") ?: "Pengingat Desa"
        val message = intent.getStringExtra("message") ?: "Waktunya cek pengajuan Anda"
        val targetClassName = intent.getStringExtra("target_activity")

        val targetIntent = if (!targetClassName.isNullOrEmpty()) {
            try {
                val clazz = Class.forName(targetClassName)
                Intent(context, clazz).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                }
            } catch (e: Exception) {
                Intent(context, MainActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                }
            }
        } else {
            Intent(context, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
        }

        NotificationHelper.showNotification(
            context = context,
            title = title,
            message = message,
            intent = targetIntent
        )
    }
}
