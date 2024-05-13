package ru.n00byara.notificationcode.ui.viewmodels

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.n00byara.notificationcode.settings.Settings
import ru.n00byara.notificationcode.terminal.Terminal
import ru.n00byara.notificationcode.ui.components.TopBarModel

class SettingsActivityViewModel : ViewModel(), LifecycleObserver {
    private val settings = Settings()

    private val _topBarUiState = MutableStateFlow(TopBarModel())
    val topBarUiState: StateFlow<TopBarModel> = _topBarUiState.asStateFlow()

    fun updateTopBarTitle(title: String) {
        this._topBarUiState.value = TopBarModel(
            title = title,
            actions = {
                IconButton(onClick = { Terminal.restartSystemui() }) {
                    Icon(imageVector = Icons.Filled.Refresh, contentDescription = "Restart")
                }
            }
        )
    }

    fun getScreenRoute(prefName: String, value: String = "") = this.settings.getString(prefName, value)

    fun setScreenRoute(prefName: String, value: String) = this.settings.setString(prefName, value)

    fun getScreenSelected(prefName: String, value: Int = 0) = this.settings.getInt(prefName, value)

    fun setScreenSelected(prefName: String, value: Int) = this.settings.setInt(prefName, value)
}