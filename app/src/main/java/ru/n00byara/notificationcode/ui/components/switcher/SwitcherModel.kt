package ru.n00byara.notificationcode.ui.components.switcher

import androidx.compose.runtime.MutableState

data class SwitcherModel(
    val title: Int,
    val prefName: String,
    val state: Boolean,
    val setState: (String, Boolean) -> Unit,
    val useCase: MutableState<Int>,
    val moduleActive: MutableState<Boolean>,
    val premissionAccess: MutableState<Boolean>
)
