package ru.n00byara.notificationcode.ui.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

data class TopBarModel @OptIn(ExperimentalMaterial3Api::class) constructor(
    var title: String = "",
    val scrollBehavior: TopAppBarScrollBehavior? = null,
    val actions: (@Composable RowScope.() -> Unit)? = null,
    val navigationIcon: (@Composable ()-> Unit)? = null
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(topBarModel: TopBarModel) {

    // Edge to Edge
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        Color.Transparent,
        darkIcons = !isSystemInDarkTheme()
    )

    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            titleContentColor = MaterialTheme.colorScheme.onBackground,
            scrolledContainerColor = MaterialTheme.colorScheme.background
        ),
        title = {
            Text(
                topBarModel.title
            )
        },
        navigationIcon = { topBarModel.navigationIcon?.invoke() },
        actions = { topBarModel.actions?.invoke(this) },
        scrollBehavior = topBarModel.scrollBehavior,
    )
}