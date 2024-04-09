package ru.n00byara.notificationcode.ui.viewmodels

import androidx.lifecycle.ViewModel
import ru.n00byara.notificationcode.models.SettingsModel

class MainActivityViewModel : ViewModel() {
    private val settings = SettingsModel()

    fun getScreenRoute(prefName: String, value: String = ""): String {
        return this.settings.getString(prefName, value)
    }

    fun setScreenRoute(prefName: String, value: String) {
        this.settings.setString(prefName, value)
    }

    fun getScreenSelected(prefName: String, value: Int = 0): Int {
        return this.settings.getInt(prefName, value)
    }

    fun setScreenSelected(prefName: String, value: Int) {
        this.settings.setInt(prefName, value)
    }
}