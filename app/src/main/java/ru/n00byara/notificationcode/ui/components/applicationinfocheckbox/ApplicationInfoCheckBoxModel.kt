package ru.n00byara.notificationcode.ui.components.applicationinfocheckbox

import ru.n00byara.notificationcode.components.application.InstalledAppInfo

data class ApplicationInfoCheckBoxModel(
    val appInfo: InstalledAppInfo,
    val checked: Boolean,
    val setState: (String, Boolean) -> Unit
)