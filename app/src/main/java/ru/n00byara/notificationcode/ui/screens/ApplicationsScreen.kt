package ru.n00byara.notificationcode.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.n00byara.notificationcode.Constants
import ru.n00byara.notificationcode.R
import ru.n00byara.notificationcode.ui.components.applicationinfocard.ApplicationInfoCheckBox
import ru.n00byara.notificationcode.ui.components.applicationinfocard.ApplicationInfoCheckBoxModel
import ru.n00byara.notificationcode.ui.viewmodels.ApplicationsScreenViewModel

@Composable
fun ApplicationsScreen(
    applicationsScreenViewModel: ApplicationsScreenViewModel = viewModel(),
    topBarTitleState: MutableState<String>
) {
    topBarTitleState.value = stringResource(R.string.screen_application_title)

    LazyColumn {
        itemsIndexed(
            applicationsScreenViewModel.getApplications()
        ) { _, appInfo ->
            val applicationInfoCheckBoxModel = ApplicationInfoCheckBoxModel(
                appInfo,
                applicationsScreenViewModel.getApplicationState(Constants.APPLICATION_PREF + appInfo.name),
                applicationsScreenViewModel::setApplicationState
            )

            ApplicationInfoCheckBox(applicationInfoCheckBoxModel)
        }
    }
}