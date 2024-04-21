package ru.n00byara.notificationcode.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.n00byara.notificationcode.ui.components.bottomnavigationbar.BottomNavBar
import ru.n00byara.notificationcode.ui.components.bottomnavigationbar.BottomNavBarModel
import ru.n00byara.notificationcode.ui.components.bottomnavigationbar.Screen
import ru.n00byara.notificationcode.ui.components.topbar.TopBar
import ru.n00byara.notificationcode.ui.components.topbar.TopBarModel
import ru.n00byara.notificationcode.ui.screens.ApplicationsScreen
import ru.n00byara.notificationcode.ui.screens.SettingsScreen
import ru.n00byara.notificationcode.ui.theme.NotificationCodeTheme
import ru.n00byara.notificationcode.ui.viewmodels.ApplicationsScreenViewModel
import ru.n00byara.notificationcode.ui.viewmodels.MainActivityViewModel
import ru.n00byara.notificationcode.ui.viewmodels.SettingsScreenViewModel

class MainActivity : ComponentActivity() {
    companion object {
        const val PREF_SCREEN_ROUTE = "selected_screen_route"
        const val PREF_SCREEN_SELECTED = "selected_screen_index"
    }
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        val settingsScreenViewModel = ViewModelProvider(this).get(SettingsScreenViewModel::class.java)
        val applicationsScreenViewModel = ViewModelProvider(this).get(ApplicationsScreenViewModel::class.java)

        setContent {
            NotificationCodeTheme {
                val topBarTitle = ""
                val topBarTitleState = remember {
                    mutableStateOf(topBarTitle)
                }
                val topBarModel = TopBarModel(topBarTitleState)

                val navController = rememberNavController()
                val bottomNavBarModel = BottomNavBarModel(
                    navController,
                    PREF_SCREEN_SELECTED,
                    mainActivityViewModel.getScreenSelected(PREF_SCREEN_SELECTED, 0),
                    mainActivityViewModel::setScreenSelected
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
                        startDestination = mainActivityViewModel.getScreenRoute(PREF_SCREEN_ROUTE, Screen.Settings.route),
                        Modifier.padding(innerPadding)
                    ) {
                        composable(Screen.Settings.route) {
                            mainActivityViewModel.setScreenRoute(PREF_SCREEN_ROUTE, Screen.Settings.route)
                            SettingsScreen(
                                settingsScreenViewModel,
                                topBarTitleState,
                            )
                        }

                        composable(Screen.ApplicationsList.route) {
                            mainActivityViewModel.setScreenRoute(PREF_SCREEN_ROUTE, Screen.ApplicationsList.route)
                            ApplicationsScreen(
                                applicationsScreenViewModel,
                                topBarTitleState
                            )
                        }
                    }
                }
            }
        }
    }
}