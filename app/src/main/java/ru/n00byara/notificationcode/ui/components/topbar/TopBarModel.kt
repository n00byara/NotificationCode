package ru.n00byara.notificationcode.ui.components.topbar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable

data class TopBarModel @OptIn(ExperimentalMaterial3Api::class) constructor(
    var title: String = "",
    val scrollBehavior: TopAppBarScrollBehavior? = null,
    val actions: (@Composable RowScope.() -> Unit)? = null,
    val navigationIcon: (@Composable ()-> Unit)? = null
)
