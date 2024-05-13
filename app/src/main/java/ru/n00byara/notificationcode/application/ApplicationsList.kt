package ru.n00byara.notificationcode.application

import android.content.Context
import android.content.pm.PackageManager

class ApplicationsList(private val context: Context) {
    private val pm = this.context.packageManager
    private val allInstalledApplications = this.pm.getInstalledApplications(PackageManager.GET_META_DATA)

    fun getApplications(): List<InstalledAppInfo> {
        val result = mutableListOf<InstalledAppInfo>()

        this.allInstalledApplications.forEach { applicationInfo ->
            val appInfo = InstalledAppInfo(this.pm, applicationInfo)

            if (!appInfo.isSystem && appInfo.name != "Shazam") {
                result.add(appInfo)
            }
        }

        return result
    }
}