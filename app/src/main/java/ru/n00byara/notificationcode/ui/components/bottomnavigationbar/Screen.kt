package ru.n00byara.notificationcode.ui.components.bottomnavigationbar

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import ru.n00byara.notificationcode.R

sealed class Screen(val route: String, @StringRes val resourceId: Int, val selectedIcon: ImageVector, val unselectedIcon: ImageVector) {
    object Settings : Screen("settings", R.string.screen_settings_title, Icons.Filled.Settings, Icons.Outlined.Settings)
    object ApplicationsList : Screen("applications", R.string.screen_application_title, Icons.Filled.List, Icons.Outlined.List)
}