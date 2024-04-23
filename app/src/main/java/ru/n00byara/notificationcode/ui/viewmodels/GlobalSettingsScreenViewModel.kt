package ru.n00byara.notificationcode.ui.viewmodels

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import com.scottyab.rootbeer.RootBeer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.n00byara.notificationcode.Constants
import ru.n00byara.notificationcode.R
import ru.n00byara.notificationcode.components.permission.Permission
import ru.n00byara.notificationcode.models.SettingsModel
import ru.n00byara.notificationcode.ui.screens.UseCaseDialogModel

class GlobalSettingsScreenViewModel(application: Application) : AndroidViewModel(application) {
    lateinit private var permission: Permission
    private val context = application.applicationContext
    private val settings = SettingsModel()
    private val openDialogState = mutableStateOf(false)
    private val _useCaseDialogState = MutableStateFlow(
        UseCaseDialogModel(this.openDialogState)
    )
    val useCaseDialogState: StateFlow<UseCaseDialogModel> = this._useCaseDialogState.asStateFlow()
    val isRoot = RootBeer(this.context).isRooted

    init {
        setVariantDialogDescription()
        this._useCaseDialogState.value.selectedCaseIndex = this.settings.getInt(Constants.USE_CASE)
        this._useCaseDialogState.value.setSelectItem = this::setUseCase
    }

    private fun setUseCase(case: Int) {
        this.permission = Permission(this.context)

        if (case == 1 && !this.permission.checkPermission()) {
            this.permission.requestPermissions()
        }

        this.settings.setInt(Constants.USE_CASE, case)
        this._useCaseDialogState.value.selectedCaseIndex = this.settings.getInt(Constants.USE_CASE)
        this.setVariantDialogDescription()
        this.openDialogState.value = false
    }

    // 0 == Root 1 == Non Root
    private fun setVariantDialogDescription() {
        if (this.settings.getInt(Constants.USE_CASE) == 0) {
            this._useCaseDialogState.value.useCaseTitle = R.string.alert_dialog_use_case_root
        } else {
            this._useCaseDialogState.value.useCaseTitle = R.string.alert_dialog_use_case_non_root
        }
    }
}