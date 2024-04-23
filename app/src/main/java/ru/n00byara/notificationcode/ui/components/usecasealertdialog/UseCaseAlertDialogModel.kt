package ru.n00byara.notificationcode.ui.components.usecasealertdialog

import androidx.compose.runtime.MutableState

data class UseCaseAlertDialogModel(
    val openDialogState: MutableState<Boolean>,
    val setSelectItem: ((Int) -> Unit)?,
    val selectedCaseIndex: Int = 0
)