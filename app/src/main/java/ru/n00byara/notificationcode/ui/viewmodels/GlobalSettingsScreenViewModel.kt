package ru.n00byara.notificationcode.ui.viewmodels

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.n00byara.notificationcode.Constants
import ru.n00byara.notificationcode.R
import ru.n00byara.notificationcode.permission.Permission
import ru.n00byara.notificationcode.settings.Settings
import ru.n00byara.notificationcode.ui.components.PermissionCardModel
import ru.n00byara.notificationcode.ui.screens.UseCaseDialogModel

class GlobalSettingsScreenViewModel(application: Application) :
    AndroidViewModel(application),
    LifecycleObserver
{
    private val permission = Permission(application.applicationContext)
    private val settings = Settings()
    private val openDialogState = mutableStateOf(false)
    private val _useCaseDialogState = MutableStateFlow(
        UseCaseDialogModel(this.openDialogState)
    )
    val useCaseDialogState: StateFlow<UseCaseDialogModel> = this._useCaseDialogState.asStateFlow()
    val permissionCardVisibilityUiState = mutableStateOf(
        this.settings.getInt(Constants.USE_CASE) == 1 && !permission.checkPermission()
    )
    private val _permissionCardModelUiState = MutableStateFlow(
        PermissionCardModel(openSettings = this::openSettings)
    )
    val permissionCardModelUiStateFlow: StateFlow<PermissionCardModel> = this._permissionCardModelUiState.asStateFlow()

    init {
        setVariantDialogDescription()
        this._useCaseDialogState.value.selectedCaseIndex = this.settings.getInt(Constants.USE_CASE)
        this._useCaseDialogState.value.setSelectItem = this::setUseCase
    }

    private fun setUseCase(case: Int) {
        this.settings.setInt(Constants.USE_CASE, case)
        this._useCaseDialogState.value.selectedCaseIndex = this.settings.getInt(Constants.USE_CASE)
        this.openDialogState.value = false
        setVariantDialogDescription()
        when (case) {
            0 -> this.permissionCardVisibilityUiState.value = false
            1 -> this.permissionCardVisibilityUiState.value = !this.permission.checkPermission()
        }
    }

    // 0 == Root 1 == Non Root
    private fun setVariantDialogDescription() {
        if (this.settings.getInt(Constants.USE_CASE) == 0) {
            this._useCaseDialogState.value.useCaseTitle = R.string.alert_dialog_use_case_root
        } else {
            this._useCaseDialogState.value.useCaseTitle = R.string.alert_dialog_use_case_non_root
        }
    }

    private fun openSettings() {
        this.permission.requestPermissions()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun onResume() {
        this.permissionCardVisibilityUiState.value =
            this.settings.getInt(Constants.USE_CASE) == 1 && !permission.checkPermission()
    }
}