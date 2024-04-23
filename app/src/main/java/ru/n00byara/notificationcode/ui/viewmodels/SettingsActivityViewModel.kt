package ru.n00byara.notificationcode.ui.viewmodels

import android.app.Application
import android.content.Intent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import com.scottyab.rootbeer.RootBeer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.n00byara.notificationcode.Constants
import ru.n00byara.notificationcode.components.permission.Permission
import ru.n00byara.notificationcode.components.terminal.Terminal
import ru.n00byara.notificationcode.models.SettingsModel
import ru.n00byara.notificationcode.ui.activities.GlobalSettingsActivity
import ru.n00byara.notificationcode.ui.components.permissionalertdialog.PermissionAlertDialogModel
import ru.n00byara.notificationcode.ui.components.topbar.TopBarModel
import ru.n00byara.notificationcode.ui.components.usecasealertdialog.UseCaseAlertDialogModel

class SettingsActivityViewModel(application: Application) : AndroidViewModel(application) {
    companion object {
        private const val IS_FIRST_LAUNCH = "is_first_launch"
    }

    private val context = application.applicationContext
    private var permission = Permission(this.context)
    private val settings = SettingsModel()
    private val _topBarUiState = MutableStateFlow(TopBarModel())
    val topBarUiState: StateFlow<TopBarModel> = _topBarUiState.asStateFlow()
    val isRooted = RootBeer(this.context).isRooted
    val isFirstLaunch = this.settings.getBoolean(IS_FIRST_LAUNCH, true)
    val openRootDialogState = mutableStateOf(false)
    val openNonRootDialogState = mutableStateOf(false)
    private val _useCaseAlertDialogUiState = MutableStateFlow(
        UseCaseAlertDialogModel(
            this.openRootDialogState,
            this::setSelectedCase,
            0
        )
    )
    val useCaseAlertDialogUiState: StateFlow<UseCaseAlertDialogModel> = this._useCaseAlertDialogUiState
    private val _permissionAlertDialogState = MutableStateFlow(
        PermissionAlertDialogModel(
            openDialogState = this.openNonRootDialogState,
            openSettings = this::openSettings
        )
    )
    val permissionAlertDialogState: StateFlow<PermissionAlertDialogModel> = this._permissionAlertDialogState

    init {
        this.openAlertDialog()
    }

    private fun openAlertDialog() {
        if (!this.isFirstLaunch) return

        if (!this.isRooted) {
            this.settings.setInt(Constants.USE_CASE, 1)
            this.openNonRootDialogState.value = true
            return
        }

        when (this.settings.getInt(Constants.USE_CASE, 2)) {
            1 -> this.openNonRootDialogState.value = true
            0 -> this.settings.setBoolean(IS_FIRST_LAUNCH, false)
            else -> this.openRootDialogState.value = true
        }
    }

    private fun openSettings() {
        this.permission.requestPermissions()
    }

    private fun setSelectedCase(case: Int) {
        this.settings.setInt(Constants.USE_CASE, case)

        when (case) {
            1 -> Terminal.restartModule()
            0 -> {
                this.settings.setBoolean(IS_FIRST_LAUNCH, false)
                this.openRootDialogState.value = false
            }
        }
    }

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