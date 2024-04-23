package ru.n00byara.notificationcode.components.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.action)) {
            context.startService(Intent(context, NotificationService::class.java))
        }
    }
}