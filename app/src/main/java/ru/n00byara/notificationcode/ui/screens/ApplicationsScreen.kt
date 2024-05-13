package ru.n00byara.notificationcode.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.n00byara.notificationcode.Constants
import ru.n00byara.notificationcode.R
import ru.n00byara.notificationcode.ui.components.ApplicationInfoCheckBox
import ru.n00byara.notificationcode.ui.components.ApplicationInfoSwitchModel
import ru.n00byara.notificationcode.ui.viewmodels.ApplicationsScreenViewModel
import kotlin.reflect.KFunction1

@Composable
fun ApplicationsScreen(
    applicationsScreenViewModel: ApplicationsScreenViewModel,
    setTopBarTitle: KFunction1<String, Unit>,
) {
    setTopBarTitle(stringResource(R.string.screen_application_title))

    LazyColumn {
        itemsIndexed(
            applicationsScreenViewModel.applications
        ) { index, appInfo ->
            val applicationInfoSwitchModel = ApplicationInfoSwitchModel(
                appInfo = appInfo,
                checked = applicationsScreenViewModel.getApplicationState(Constants.APPLICATION_PREF + appInfo.packageName),
                setState = applicationsScreenViewModel::setApplicationState
            )

            ApplicationInfoCheckBox(applicationInfoSwitchModel = applicationInfoSwitchModel)
            if (index < applicationsScreenViewModel.applications.lastIndex) {
                Divider(
                    modifier = Modifier.padding(start = 20.dp, end = 20.dp),
                    color = Color.Gray
                )
            }
        }
    }
}