package ru.n00byara.notificationcode.applicationinfo

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable

class InstalledAppInfo(private val pm: PackageManager, private val applicationInfo: ApplicationInfo) {
    val name: String
    val icon: Drawable
    val isSystem: Boolean

    init {
        name = applicationInfo.loadLabel(pm).toString()
        isSystem = !((applicationInfo.flags and ApplicationInfo.FLAG_SYSTEM) == 0)

        icon = applicationInfo.loadIcon(pm)
    }
}