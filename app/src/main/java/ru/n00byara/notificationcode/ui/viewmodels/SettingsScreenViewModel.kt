package ru.n00byara.notificationcode.ui.viewmodels

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.scottyab.rootbeer.RootBeer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.n00byara.notificationcode.Constants
import ru.n00byara.notificationcode.permission.Permission
import ru.n00byara.notificationcode.settings.Settings
import ru.n00byara.notificationcode.ui.components.PermissionAlertDialogModel
import ru.n00byara.notificationcode.ui.components.PermissionCardModel
import ru.n00byara.notificationcode.ui.components.UseCaseAlertDialogModel

class SettingsScreenViewModel(private val application: Application) :
    AndroidViewModel(application),
    LifecycleObserver
{
    companion object {
        private const val IS_FIRST_LAUNCH = "is_first_launch"
    }

    private val context = this.application.applicationContext
    private val settingsModel = Settings()
    private var permission = Permission(this.context)
    private val settings = Settings()
    private val isFirstLaunch = this.settings.getBoolean(IS_FIRST_LAUNCH, true)

    val isRootedDevice = RootBeer(this.context).isRooted
    val openRootDialogState = mutableStateOf(false)
    val openNonRootDialogState = mutableStateOf(false)
    val useCase = mutableStateOf(this.settings.getInt(Constants.USE_CASE, 1))
    val moduleActive = mutableStateOf(this.settingsModel.isActive)
    val permissionAccess = mutableStateOf(this.permission.checkPermission())
    val permissionCardVisibilityUiState = mutableStateOf(
        this.settings.getInt(Constants.USE_CASE) == 1 && !permission.checkPermission()
    )

    private val _useCaseAlertDialogUiState = MutableStateFlow(
        UseCaseAlertDialogModel(
            openDialogState = this.openRootDialogState,
            setSelectItem = this::setSelectedCase,
            selectedCaseIndex = 0
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

    val visibilityLsposedCardState = mutableStateOf(
        this.settings.getInt(Constants.USE_CASE, 0) == 0 && this.isRootedDevice
    )

    private val _permissionCardModelUiState = MutableStateFlow(
        PermissionCardModel(openSettings = this::openSettings)
    )
    val permissionCardModelUiStateFlow: StateFlow<PermissionCardModel> = this._permissionCardModelUiState.asStateFlow()

    init {
        this.openAlertDialog()
    }

    private fun openAlertDialog() {
        if (!this.isFirstLaunch) return

        if (this.isRootedDevice) {
            this.visibilityLsposedCardState.value = true
            this.openRootDialogState.value = true
            return
        }

        this.settings.setInt(Constants.USE_CASE, 1)
        this.openNonRootDialogState.value = true
        this.useCase.value = this.settings.getInt(Constants.USE_CASE)
    }

    private fun openSettings() {
        this.settings.setBoolean(IS_FIRST_LAUNCH, false)
        this.permission.requestPermissions()
    }

    private fun setSelectedCase(case: Int) {
        this.settings.setInt(Constants.USE_CASE, case)
        this.settings.setBoolean(IS_FIRST_LAUNCH, false)
        this.openRootDialogState.value = false
        this.useCase.value = case

        if (case != 1) return
        this.openNonRootDialogState.value = true
        this.visibilityLsposedCardState.value = false
    }

    fun getBoolean(prefName: String): Boolean = this.settingsModel.getBoolean(prefName)

    fun setBoolean(prefName: String, value: Boolean) = this.settingsModel.setBoolean(prefName, value)

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun onResume() {
        this.visibilityLsposedCardState.value =
            this.settings.getInt(Constants.USE_CASE) == 0 && this.isRootedDevice
        this.permissionCardVisibilityUiState.value =
            this.settings.getInt(Constants.USE_CASE) == 1 && !permission.checkPermission()
        this.permissionAccess.value = this.permission.checkPermission()
    }
}