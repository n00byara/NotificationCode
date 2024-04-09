package ru.n00byara.notificationcode.models

import com.highcapable.yukihookapi.YukiHookAPI
import com.highcapable.yukihookapi.hook.factory.prefs
import com.highcapable.yukihookapi.hook.xposed.application.ModuleApplication
import ru.n00byara.notificationcode.Constants

class SettingsModel {
    private val prefs = ModuleApplication.appContext.prefs(Constants.SETTINGS_NAME)
    val isActive = YukiHookAPI.Status.isXposedModuleActive

    fun getBoolean(prefName: String): Boolean {
        return this.prefs.getBoolean(prefName, )
    }

    fun setBoolean(prefName: String, value: Boolean) {
        this.prefs.edit()
            .putBoolean(prefName, value)
            .apply()
    }

    fun getString(prefName: String, value: String = ""): String {
        return this.prefs.getString(prefName, value)
    }

    fun setString(prefName: String, value: String) {
        this.prefs.edit()
            .putString(prefName, value)
            .apply()
    }

    fun getInt(prefName: String, value: Int = 0): Int {
        return this.prefs.getInt(prefName, value)
    }

    fun setInt(prefName: String, value: Int) {
        this.prefs.edit()
            .putInt(prefName, value)
            .apply()
    }
}