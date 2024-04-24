package ru.n00byara.notificationcode.ui.viewmodels

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.n00byara.notificationcode.components.terminal.Terminal
import ru.n00byara.notificationcode.ui.components.topbar.TopBarModel
import kotlin.reflect.KFunction0

class GlobalSettingsActivityViewModel(private val finishActivity: KFunction0<Unit>) : ViewModel() {
    private val _topBarUiState = MutableStateFlow(TopBarModel())
    val topBarUiState: StateFlow<TopBarModel> = this._topBarUiState.asStateFlow()

    fun setTopBarTitle(title: String) {
        this._topBarUiState.value = TopBarModel(
            title,
            actions = {
                IconButton(onClick = {
                    Terminal.restartSystemui()
                }) {
                    Icon(
                        imageVector = Icons.Filled.Refresh,
                        contentDescription = "restart"
                    )
                }
            },
            navigationIcon = {
                IconButton(onClick = {
                    this.finishActivity()
                }) {
                    Icon(Icons.Filled.ArrowBack, "Back")
                }
            }
        )
    }
}

class GlobalSettingsActivityViewModelFactory(
    private val finishActivity: KFunction0<Unit>
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GlobalSettingsActivityViewModel::class.java)) {
            return GlobalSettingsActivityViewModel(this.finishActivity) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}