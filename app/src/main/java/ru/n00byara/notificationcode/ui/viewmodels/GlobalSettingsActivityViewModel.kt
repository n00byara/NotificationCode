package ru.n00byara.notificationcode.ui.viewmodels

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.n00byara.notificationcode.terminal.Terminal
import ru.n00byara.notificationcode.ui.components.TopBarModel

class GlobalSettingsActivityViewModel : ViewModel() {
    private val _topBarUiState = MutableStateFlow(TopBarModel())
    val topBarUiState: StateFlow<TopBarModel> = this._topBarUiState.asStateFlow()

    private val _shouldFinishLiveData = MutableLiveData<Boolean>()
    val shouldFinishLiveData: LiveData<Boolean> = this._shouldFinishLiveData

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
                    this._shouldFinishLiveData.postValue(true)
                }) {
                    Icon(Icons.Filled.ArrowBack, "Back")
                }
            }
        )
    }
}