package ru.n00byara.notificationcode.application

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import ru.n00byara.notificationcode.Constants
import ru.n00byara.notificationcode.settings.Settings

class InstalledAppInfo(private val pm: PackageManager, private val applicationInfo: ApplicationInfo) {
    val settings = Settings()
    val name: String
    val icon: Drawable
    val isSystem: Boolean
    val packageName: String
    val isActive: Boolean

    init {
        this.name = this.applicationInfo.loadLabel(this.pm).toString()
        this.packageName = this.applicationInfo.packageName
        this.isSystem = !((this.applicationInfo.flags and ApplicationInfo.FLAG_SYSTEM) == 0)
        this.isActive = this.settings.getBoolean(Constants.APPLICATION_PREF + packageName)
        this.icon = this.applicationInfo.loadIcon(this.pm)
    }
}