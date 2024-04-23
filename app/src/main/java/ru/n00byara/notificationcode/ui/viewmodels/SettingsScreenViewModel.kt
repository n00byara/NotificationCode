package ru.n00byara.notificationcode.ui.viewmodels

import androidx.lifecycle.ViewModel
import ru.n00byara.notificationcode.Constants
import ru.n00byara.notificationcode.models.SettingsModel

class SettingsScreenViewModel : ViewModel() {

    private val settingsModel = SettingsModel()
    val isActive = this.settingsModel.isActive
    val isRoot = this.settingsModel.getInt(Constants.USE_CASE) == 0
    fun getBoolean(prefName: String): Boolean {
        return this.settingsModel.getBoolean(prefName)
    }

    fun setBoolean(prefName: String, value: Boolean) {
        this.settingsModel.setBoolean(prefName, value)
    }
}