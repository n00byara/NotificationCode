package ru.n00byara.notificationcode.ui.components.applicationinfocard

import ru.n00byara.notificationcode.applicationinfo.InstalledAppInfo

data class ApplicationInfoCardModel(
    val appInfo: InstalledAppInfo,
    val checked: Boolean,
    val setState: (String, Boolean) -> Unit
)