package ru.n00byara.notificationcode.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.n00byara.notificationcode.R
import ru.n00byara.notificationcode.ui.components.permissioncard.PermissionCard
import ru.n00byara.notificationcode.ui.components.usecasealertdialog.UseCaseAlertDialog
import ru.n00byara.notificationcode.ui.components.usecasealertdialog.UseCaseAlertDialogModel
import ru.n00byara.notificationcode.ui.viewmodels.GlobalSettingsScreenViewModel

@Composable
fun GlobalSettingsScreen(
    globalSettingsScreenViewModel: GlobalSettingsScreenViewModel = viewModel(),
    setTopBarTitle: (String) -> Unit,
    paddingValues: PaddingValues
) {
    setTopBarTitle(stringResource(R.string.screen_global_settings_title))

    LazyColumn(
        Modifier.padding(paddingValues)
    ) {
        item {
            val useCaseDialogModel by globalSettingsScreenViewModel.useCaseDialogState.collectAsState()
            UseCaseDialog(useCaseDialogModel)
        }

        val permissionCardVisibilityUiState = globalSettingsScreenViewModel.permissionCardVisibilityUiState

        item {
            AnimatedVisibility(
                visible = permissionCardVisibilityUiState.value
            ) {
                val permissionCardModel by globalSettingsScreenViewModel.permissionCardModelUiStateFlow.collectAsState()
                PermissionCard(permissionCardModel)
            }
        }
    }
}

data class UseCaseDialogModel(
    val openDialogState: MutableState<Boolean>,
    var useCaseTitle: Int = 0,
    var setSelectItem: ((Int) -> Unit)? = null,
    var selectedCaseIndex: Int = 0
)

@Composable
fun UseCaseDialog(
    useCaseDialogModel: UseCaseDialogModel,
) {
    useCaseDialogModel.openDialogState.value


    val useCaseAlertDialogModel = UseCaseAlertDialogModel(
        useCaseDialogModel.openDialogState,
        useCaseDialogModel.setSelectItem,
        useCaseDialogModel.selectedCaseIndex
    )

    if (useCaseAlertDialogModel.openDialogState.value) {
        UseCaseAlertDialog(useCaseAlertDialogModel)
    }

    Card(
        Modifier
            .padding(start = 15.dp, end = 15.dp, top = 7.dp, bottom = 7.dp)
            .wrapContentHeight()
            .clip(RoundedCornerShape(19.dp))
            .clickable {
                useCaseAlertDialogModel.openDialogState.value =
                    !useCaseAlertDialogModel.openDialogState.value
            }

    ) {
        Column(
            Modifier
                .padding(10.dp)
                .fillMaxWidth()

        ) {
            Row {
                Text(
                    stringResource(R.string.alert_dialog_use_case),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Row {
                Text(
                    stringResource(useCaseDialogModel.useCaseTitle),
                    fontSize = 15.sp
                )
            }
        }
    }
}