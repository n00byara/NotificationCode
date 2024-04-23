package ru.n00byara.notificationcode.components.permission

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/*  This class is bullshit because I don't  find way of start activity from view model. */

class PermissionRequestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            requestPermissions()
        }
    }

    private fun requestPermissions() {
        val intent = Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS")
        startActivityForResult(intent, 0)
        finish()
    }
}