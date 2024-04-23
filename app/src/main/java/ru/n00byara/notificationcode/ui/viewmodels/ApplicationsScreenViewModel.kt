package ru.n00byara.notificationcode.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import ru.n00byara.notificationcode.components.application.ApplicationsList
import ru.n00byara.notificationcode.models.SettingsModel

class ApplicationsScreenViewModel(application: Application) : AndroidViewModel(application) {
    private val context = application.applicationContext
    private val applicationsList = ApplicationsList(this.context)
    private val settings = SettingsModel()
    val applications = applicationsList.getApplications()

    fun getApplicationState(prefName: String): Boolean {
        return this.settings.getBoolean(prefName)
    }

    fun setApplicationState(prefName: String, value: Boolean) {
        this.settings.setBoolean(prefName, value)
    }
}