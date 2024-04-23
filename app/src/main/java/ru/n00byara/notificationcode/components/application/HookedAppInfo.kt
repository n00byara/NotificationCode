package ru.n00byara.notificationcode.components.application

import android.content.Context
import android.content.pm.ApplicationInfo
import com.highcapable.yukihookapi.hook.factory.prefs
import com.highcapable.yukihookapi.hook.xposed.application.ModuleApplication
import ru.n00byara.notificationcode.Constants

class HookedAppInfo(hookedContext: Context) {
    val prefs = ModuleApplication().prefs(Constants.SETTINGS_NAME)
    val isActive: Boolean
    val isSystem: Boolean
    val name: String
    val packageName: String

    init {
        val pm = hookedContext.packageManager
        val applicationInfo = hookedContext.applicationInfo

        name = applicationInfo.loadLabel(pm).toString()
        packageName = applicationInfo.packageName
        isSystem = !((applicationInfo.flags and ApplicationInfo.FLAG_SYSTEM) == 0)
        isActive = prefs.getBoolean(Constants.APPLICATION_PREF + packageName)
    }
}