package ru.n00byara.notificationcode.ui.viewmodels

import android.app.Application
import android.content.pm.PackageManager
import androidx.lifecycle.AndroidViewModel
import ru.n00byara.notificationcode.applicationinfo.InstalledAppInfo
import ru.n00byara.notificationcode.models.SettingsModel

class ApplicationsScreenViewModel(application: Application) : AndroidViewModel(application) {
    private val context = application.applicationContext
    private val pm = context.packageManager
    private val allInstalledApplications = pm.getInstalledApplications(PackageManager.GET_META_DATA)
    private val settings = SettingsModel()

    fun getApplications(): List<InstalledAppInfo> {
        var result = mutableListOf<InstalledAppInfo>()

        allInstalledApplications.forEach { applicationInfo ->
            val appInfo = InstalledAppInfo(this.pm, applicationInfo)

            if (!appInfo.isSystem && appInfo.name != "Shazam") {
                result.add(appInfo)
            }
        }

        return result
    }

    fun getApplicationState(prefName: String): Boolean {
        return this.settings.getBoolean(prefName)
    }

    fun setApplicationState(prefName: String, value: Boolean) {
        this.settings.setBoolean(prefName, value)
    }
}