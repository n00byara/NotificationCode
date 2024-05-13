package ru.n00byara.notificationcode.ui.viewmodels

import androidx.lifecycle.ViewModel
import ru.n00byara.notificationcode.settings.Settings

class SettingsScreenViewModel : ViewModel() {
    private val settings = Settings()

    val moduleActive = this.settings.isActive

    fun getBoolean(prefName: String): Boolean = this.settings.getBoolean(prefName)

    fun setBoolean(prefName: String, value: Boolean) = this.settings.setBoolean(prefName, value)
}