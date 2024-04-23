package ru.n00byara.notificationcode.components.application

import android.content.Context
import android.content.pm.PackageManager

class ApplicationsList(private val context: Context) {
    private val pm = context.packageManager
    private val allInstalledApplications = pm.getInstalledApplications(PackageManager.GET_META_DATA)

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

    fun getAllApplications(): List<InstalledAppInfo> {
        var result = mutableListOf<InstalledAppInfo>()

        allInstalledApplications.forEach { applicationInfo ->
            val appInfo = InstalledAppInfo(this.pm, applicationInfo)

            if (!appInfo.isSystem) {
                result.add(appInfo)
            }
        }

        return result
    }
}