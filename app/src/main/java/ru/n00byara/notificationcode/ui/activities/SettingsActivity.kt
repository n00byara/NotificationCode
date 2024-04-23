package ru.n00byara.notificationcode.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.n00byara.notificationcode.ui.components.bottomnavigationbar.BottomNavBar
import ru.n00byara.notificationcode.ui.components.bottomnavigationbar.BottomNavBarModel
import ru.n00byara.notificationcode.ui.components.bottomnavigationbar.Screen
import ru.n00byara.notificationcode.ui.components.permissionalertdialog.PermissionAlertDialog
import ru.n00byara.notificationcode.ui.components.topbar.TopBar
import ru.n00byara.notificationcode.ui.components.usecasealertdialog.UseCaseAlertDialog
import ru.n00byara.notificationcode.ui.screens.ApplicationsScreen
import ru.n00byara.notificationcode.ui.screens.SettingsScreen
import ru.n00byara.notificationcode.ui.theme.NotificationCodeTheme
import ru.n00byara.notificationcode.ui.viewmodels.ApplicationsScreenViewModel
import ru.n00byara.notificationcode.ui.viewmodels.SettingsActivityViewModel
import ru.n00byara.notificationcode.ui.viewmodels.SettingsScreenViewModel

class SettingsActivity : ComponentActivity() {
    companion object {
        const val PREF_SCREEN_ROUTE = "selected_screen_route"
        const val PREF_SCREEN_SELECTED = "selected_screen_index"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val settingsActivityViewModel = ViewModelProvider(this).get(SettingsActivityViewModel::class.java)
        val settingsScreenViewModel = ViewModelProvider(this).get(SettingsScreenViewModel::class.java)
        val applicationsScreenViewModel = ViewModelProvider(this).get(ApplicationsScreenViewModel::class.java)

        setContent {
            NotificationCodeTheme {
                val openRootDialogState = settingsActivityViewModel.openRootDialogState
                if (openRootDialogState.value) {
                    val useCaseAlertDialogModel by settingsActivityViewModel.useCaseAlertDialogUiState.collectAsState()
                    UseCaseAlertDialog(useCaseAlertDialogModel)
                }

                val openNonRootDialogState = settingsActivityViewModel.openNonRootDialogState
                if (openNonRootDialogState.value) {
                    val permissionAlertDialogModel by settingsActivityViewModel.permissionAlertDialogState.collectAsState()
                    PermissionAlertDialog(permissionAlertDialogModel)
                }


                val topBarModel by settingsActivityViewModel.topBarUiState.collectAsState()

                val navController = rememberNavController()

                val bottomNavBarModel = BottomNavBarModel(
                    navController = navController,
                    prefName = PREF_SCREEN_SELECTED,
                    selected = settingsActivityViewModel.getScreenSelected(PREF_SCREEN_SELECTED, 0),
                    setSelected = settingsActivityViewModel::setScreenSelected
                )

                Scaffold(
                    topBar = {
                        TopBar(topBarModel)
                    },
                    bottomBar = {
                        BottomNavBar(bottomNavBarModel)
                    }
                ) { innerPadding ->
                    NavHost(
                        navController,
                        settingsActivityViewModel.getScreenRoute(PREF_SCREEN_ROUTE, Screen.Settings.route),
                        Modifier.padding(innerPadding)
                    ) {
                        composable(Screen.Settings.route) {
                            settingsActivityViewModel.setScreenRoute(PREF_SCREEN_ROUTE, Screen.Settings.route)
                            SettingsScreen(
                                settingsScreenViewModel,
                                settingsActivityViewModel::updateTopBarTitle
                            )
                        }

                        composable(Screen.ApplicationsList.route) {
                            settingsActivityViewModel.setScreenRoute(PREF_SCREEN_ROUTE, Screen.ApplicationsList.route)
                            ApplicationsScreen(
                                applicationsScreenViewModel,
                                settingsActivityViewModel::updateTopBarTitle
                            )
                        }
                    }
                }
            }
        }
    }
}