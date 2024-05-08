package ru.n00byara.notificationcode.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.Observer
import ru.n00byara.notificationcode.ui.components.TopBar
import ru.n00byara.notificationcode.ui.theme.NotificationCodeTheme
import ru.n00byara.notificationcode.ui.screens.GlobalSettingsScreen
import ru.n00byara.notificationcode.ui.viewmodels.GlobalSettingsActivityViewModel
import ru.n00byara.notificationcode.ui.viewmodels.GlobalSettingsScreenViewModel

class GlobalSettingsActivity : ComponentActivity() {
    val globalSettingsScreenViewModel by viewModels<GlobalSettingsScreenViewModel>()
    val globalSettingsActivityViewModel by viewModels<GlobalSettingsActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycle.addObserver(globalSettingsScreenViewModel)

        globalSettingsActivityViewModel.shouldFinishLiveData.observe(
            this,
            Observer { finish() }
        )

        setContent {
            NotificationCodeTheme {
                val topBarModel by globalSettingsActivityViewModel.topBarUiState.collectAsState()

                Scaffold(
                    topBar = {
                        TopBar(topBarModel)
                    }
                ) { paddingValues ->
                    GlobalSettingsScreen(
                        globalSettingsScreenViewModel = globalSettingsScreenViewModel,
                        setTopBarTitle = globalSettingsActivityViewModel::setTopBarTitle,
                        paddingValues = paddingValues)
                }
            }
        }
    }
}