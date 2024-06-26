package ru.n00byara.notificationcode.settings

import com.highcapable.yukihookapi.YukiHookAPI.Status.isXposedModuleActive
import com.highcapable.yukihookapi.hook.factory.prefs
import com.highcapable.yukihookapi.hook.xposed.application.ModuleApplication.Companion.appContext
import ru.n00byara.notificationcode.Constants

class Settings {
    private val prefs = appContext.prefs(Constants.SETTINGS_NAME)
    val isActive = isXposedModuleActive

    fun getBoolean(prefName: String, value: Boolean = false) = this.prefs.getBoolean(prefName, value)

    fun setBoolean(prefName: String, value: Boolean) {
        this.prefs.edit()
            .putBoolean(prefName, value)
            .apply()
    }

    fun getString(prefName: String, value: String = "") = this.prefs.getString(prefName, value)

    fun setString(prefName: String, value: String) {
        this.prefs.edit()
            .putString(prefName, value)
            .apply()
    }

    fun getInt(prefName: String, value: Int = 0) = this.prefs.getInt(prefName, value)

    fun setInt(prefName: String, value: Int) {
        this.prefs.edit()
            .putInt(prefName, value)
            .apply()
    }
}