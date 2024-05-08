package ru.n00byara.notificationcode.application

import android.content.Context
import android.content.pm.ApplicationInfo
import com.highcapable.yukihookapi.hook.factory.prefs
import com.highcapable.yukihookapi.hook.xposed.application.ModuleApplication
import ru.n00byara.notificationcode.Constants

class HookedAppInfo(private val hookedContext: Context) {
    val prefs = ModuleApplication().prefs(Constants.SETTINGS_NAME)
    val isActive: Boolean
    val isSystem: Boolean
    val name: String
    val packageName: String

    init {
        val pm = this.hookedContext.packageManager
        val applicationInfo = this.hookedContext.applicationInfo

        this.name = applicationInfo.loadLabel(pm).toString()
        this.packageName = applicationInfo.packageName
        this.isSystem = !((applicationInfo.flags and ApplicationInfo.FLAG_SYSTEM) == 0)
        this.isActive = this.prefs.getBoolean(Constants.APPLICATION_PREF + packageName)
    }
}