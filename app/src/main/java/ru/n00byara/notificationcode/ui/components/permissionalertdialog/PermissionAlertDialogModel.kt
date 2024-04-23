package ru.n00byara.notificationcode.ui.components.permissionalertdialog

import androidx.compose.runtime.MutableState

data class PermissionAlertDialogModel(
    val openDialogState: MutableState<Boolean>,
    val openSettings: () -> Unit
)