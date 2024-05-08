package ru.n00byara.notificationcode.ui.viewmodels

import android.app.Application
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.scottyab.rootbeer.RootBeer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.n00byara.notificationcode.settings.Settings
import ru.n00byara.notificationcode.ui.components.TopBarModel

class SettingsActivityViewModel(application: Application) : AndroidViewModel(application) {
    private val context = application.applicationContext
    private val settings = Settings()
    private val isRooted = RootBeer(this.context).isRooted

    private val _topBarUiState = MutableStateFlow(TopBarModel())
    val topBarUiState: StateFlow<TopBarModel> = _topBarUiState.asStateFlow()

    private val _shouldStartActivityLiveData = MutableLiveData<Boolean>()
    val shouldFinishLiveData: LiveData<Boolean> = this._shouldStartActivityLiveData

    fun updateTopBarTitle(title: String) {
        this._topBarUiState.value = TopBarModel(
            title = title,
            actions = if (this.isRooted) {
                {
                    IconButton(onClick = {
                        this@SettingsActivityViewModel._shouldStartActivityLiveData.postValue(true)
                    }) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Settings"
                        )
                    }
                }
            } else {
                null
            }
        )
    }

    fun getScreenRoute(prefName: String, value: String = "") = this.settings.getString(prefName, value)

    fun setScreenRoute(prefName: String, value: String) = this.settings.setString(prefName, value)

    fun getScreenSelected(prefName: String, value: Int = 0) = this.settings.getInt(prefName, value)

    fun setScreenSelected(prefName: String, value: Int) = this.settings.setInt(prefName, value)
}