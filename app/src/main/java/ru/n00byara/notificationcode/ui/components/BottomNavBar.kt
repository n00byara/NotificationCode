package ru.n00byara.notificationcode.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState

data class BottomNavBarModel(
    val navController: NavController,
    val prefName: String,
    val selected: Int,
    val setSelected: (String, Int) -> Unit
)

@Composable
fun BottomNavBar(bottomNavBarModel: BottomNavBarModel) {
    val items = listOf(Screen.Settings, Screen.ApplicationsList)
    val selectedState by remember { mutableStateOf(bottomNavBarModel.selected) }

    NavigationBar(
        containerColor = Color.Transparent,
    ) {
        val navBackStackEntry by bottomNavBarModel.navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        items.forEachIndexed { index, screen ->
            val selected = selectedState == index

            bottomNavBarModel.setSelected(bottomNavBarModel.prefName, index)

            NavigationBarItem(
                icon = {
                    Icon(
                        if (selected) screen.selectedIcon else screen.unselectedIcon,
                        stringResource(screen.resourceId)
                    )
                },
                label = { Text(stringResource(screen.resourceId)) },
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {
                    bottomNavBarModel.navController.navigate(screen.route) {
                        popUpTo(bottomNavBarModel.navController.graph.findStartDestination().id) {
                            saveState = true
                        }

                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}