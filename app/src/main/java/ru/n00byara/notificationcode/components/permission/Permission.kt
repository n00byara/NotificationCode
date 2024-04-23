package ru.n00byara.notificationcode.components.permission

import android.content.Context
import android.content.Intent
import android.provider.Settings

class Permission(private val context: Context) {
    fun requestPermissions() {
        val intent = Intent(this.context, PermissionRequestActivity::class.java)
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }

    fun checkPermission(): Boolean {
        val notificationListener =
            arrayOf(Settings.Secure.getString(context.contentResolver, "enabled_notification_listeners")).joinToString()

        return notificationListener.contains(this.context.packageName)
    }
}