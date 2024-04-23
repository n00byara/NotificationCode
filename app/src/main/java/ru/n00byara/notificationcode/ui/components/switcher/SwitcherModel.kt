package ru.n00byara.notificationcode.ui.components.switcher

data class SwitcherModel(
    val title: Int,
    val prefName: String,
    val state: Boolean,
    val setState: (String, Boolean) -> Unit,
    val moduleActive: Boolean,
    val isRoot: Boolean
)
