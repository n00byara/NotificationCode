package ru.n00byara.notificationcode.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import ru.n00byara.notificationcode.application.ApplicationsList
import ru.n00byara.notificationcode.settings.Settings

class ApplicationsScreenViewModel(application: Application) : AndroidViewModel(application) {
    private val context = application.applicationContext
    private val applicationsList = ApplicationsList(this.context)
    private val settings = Settings()
    val applications = this.applicationsList.getApplications()

    fun getApplicationState(prefName: String) = this.settings.getBoolean(prefName)

    fun setApplicationState(prefName: String, value: Boolean) = this.settings.setBoolean(prefName, value)
}