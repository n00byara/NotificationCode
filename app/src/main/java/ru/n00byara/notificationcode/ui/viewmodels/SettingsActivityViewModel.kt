package ru.n00byara.notificationcode.ui.viewmodels

import android.app.Application
import android.content.Intent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.lifecycle.AndroidViewModel
import com.scottyab.rootbeer.RootBeer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.n00byara.notificationcode.models.SettingsModel
import ru.n00byara.notificationcode.ui.activities.GlobalSettingsActivity
import ru.n00byara.notificationcode.ui.components.topbar.TopBarModel

class SettingsActivityViewModel(application: Application) : AndroidViewModel(application) {
    private val context = application.applicationContext
    private val settings = SettingsModel()
    private val isRooted = RootBeer(this.context).isRooted
    private val _topBarUiState = MutableStateFlow(TopBarModel())
    val topBarUiState: StateFlow<TopBarModel> = _topBarUiState.asStateFlow()

    fun updateTopBarTitle(title: String) {
        this._topBarUiState.value = TopBarModel(
            title = title,
            actions = if (this.isRooted) {
                {
                    IconButton(onClick = {
                        val intent = Intent(this@SettingsActivityViewModel.context, GlobalSettingsActivity::class.java)
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        this@SettingsActivityViewModel.context.startActivity(intent)
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

    fun getScreenRoute(prefName: String, value: String = ""): String {
        return this.settings.getString(prefName, value)
    }

    fun setScreenRoute(prefName: String, value: String) {
        this.settings.setString(prefName, value)
    }

    fun getScreenSelected(prefName: String, value: Int = 0): Int {
        return this.settings.getInt(prefName, value)
    }

    fun setScreenSelected(prefName: String, value: Int) {
        this.settings.setInt(prefName, value)
    }
}