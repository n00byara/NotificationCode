package ru.n00byara.notificationcode.components.application

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import ru.n00byara.notificationcode.Constants
import ru.n00byara.notificationcode.models.SettingsModel

class InstalledAppInfo(private val pm: PackageManager, private val applicationInfo: ApplicationInfo) {
    val settingsModel = SettingsModel()
    val name: String
    val icon: Drawable
    val isSystem: Boolean
    val packageName: String
    val isActive: Boolean

    init {
        name = applicationInfo.loadLabel(pm).toString()
        packageName = applicationInfo.packageName
        isSystem = !((applicationInfo.flags and ApplicationInfo.FLAG_SYSTEM) == 0)
        isActive = settingsModel.getBoolean(Constants.APPLICATION_PREF + packageName)
        icon = applicationInfo.loadIcon(pm)
    }
}