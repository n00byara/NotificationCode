package ru.n00byara.notificationcode.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModelProvider
import ru.n00byara.notificationcode.ui.components.topbar.TopBar
import ru.n00byara.notificationcode.ui.theme.NotificationCodeTheme
import ru.n00byara.notificationcode.ui.screens.GlobalSettingsScreen
import ru.n00byara.notificationcode.ui.viewmodels.GlobalSettingsActivityViewModel
import ru.n00byara.notificationcode.ui.viewmodels.GlobalSettingsActivityViewModelFactory
import ru.n00byara.notificationcode.ui.viewmodels.GlobalSettingsScreenViewModel

class GlobalSettingsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val globalSettingsScreenViewModel = ViewModelProvider(this).get(GlobalSettingsScreenViewModel::class.java)
        val globalSettingsActivityViewModel = ViewModelProvider(this, GlobalSettingsActivityViewModelFactory(this::finish)).get(GlobalSettingsActivityViewModel::class.java)

        lifecycle.addObserver(globalSettingsScreenViewModel)

        setContent {
            NotificationCodeTheme {
                val topBarModel by globalSettingsActivityViewModel.topBarUiState.collectAsState()

                Scaffold(
                    topBar = {
                        TopBar(topBarModel)
                    }
                ) { paddingValues ->
                    GlobalSettingsScreen(globalSettingsScreenViewModel, globalSettingsActivityViewModel::setTopBarTitle, paddingValues)
                }
            }
        }
    }
}