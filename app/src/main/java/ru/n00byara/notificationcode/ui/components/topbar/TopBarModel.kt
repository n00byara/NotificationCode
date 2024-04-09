package ru.n00byara.notificationcode.ui.components.topbar

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.MutableState

data class TopBarModel @OptIn(ExperimentalMaterial3Api::class) constructor(
    val title: MutableState<String>,
    val scrollBehavior: TopAppBarScrollBehavior
)
