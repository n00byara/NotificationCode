package ru.n00byara.notificationcode.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.n00byara.notificationcode.Constants
import ru.n00byara.notificationcode.R
import ru.n00byara.notificationcode.ui.components.applicationinfocheckbox.ApplicationInfoCheckBox
import ru.n00byara.notificationcode.ui.components.applicationinfocheckbox.ApplicationInfoCheckBoxModel
import ru.n00byara.notificationcode.ui.viewmodels.ApplicationsScreenViewModel
import kotlin.reflect.KFunction1

@Composable
fun ApplicationsScreen(
    applicationsScreenViewModel: ApplicationsScreenViewModel = viewModel(),
    setTopBarTitle: KFunction1<String, Unit>,
) {
    setTopBarTitle(stringResource(R.string.screen_application_title))

    LazyColumn {
        itemsIndexed(
            applicationsScreenViewModel.applications
        ) { _, appInfo ->
            val applicationInfoCheckBoxModel = ApplicationInfoCheckBoxModel(
                appInfo,
                applicationsScreenViewModel.getApplicationState(Constants.APPLICATION_PREF + appInfo.packageName),
                applicationsScreenViewModel::setApplicationState
            )

            ApplicationInfoCheckBox(applicationInfoCheckBoxModel)
        }
    }
}