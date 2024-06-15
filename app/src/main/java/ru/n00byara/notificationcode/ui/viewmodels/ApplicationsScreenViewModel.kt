package ru.n00byara.notificationcode.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.highcapable.yukihookapi.hook.xposed.application.ModuleApplication.Companion.appContext
import ru.n00byara.notificationcode.application.ApplicationsList
import ru.n00byara.notificationcode.settings.Settings

class ApplicationsScreenViewModel(
    private val settings: Settings
) : ViewModel() {
    private val applicationsList = ApplicationsList(appContext)
    val applications = this.applicationsList.getApplications()

    fun getApplicationState(prefName: String) = this.settings.getBoolean(prefName)

    fun setApplicationState(prefName: String, value: Boolean) = this.settings.setBoolean(prefName, value)
}