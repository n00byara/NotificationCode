package ru.n00byara.notificationcode.ui.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.n00byara.notificationcode.Constants
import ru.n00byara.notificationcode.R
import ru.n00byara.notificationcode.ui.components.applicationinfocard.ApplicationInfoCard
import ru.n00byara.notificationcode.ui.components.applicationinfocard.ApplicationInfoCardModel
import ru.n00byara.notificationcode.ui.viewmodels.ApplicationsScreenViewModel

@Composable
fun ApplicationsScreen(
    applicationsScreenViewModel: ApplicationsScreenViewModel = viewModel(),
    topBarTitleState: MutableState<String>
) {
    topBarTitleState.value = stringResource(R.string.screen_application_title)

    LazyColumn {
        item {
            ApplicationInfoCard()
        }

        itemsIndexed(
            applicationsScreenViewModel.getApplications()
        ) { _, appInfo ->
            val applicationInfoCardModel = ApplicationInfoCardModel(
                appInfo,
                applicationsScreenViewModel.getApplicationState(Constants.APPLICATION_PREF + appInfo.name),
                applicationsScreenViewModel::setApplicationState
            )

            ApplicationInfoCard(applicationInfoCardModel)
        }
    }
}

@Composable
fun ApplicationInfoCard() {
    Card(
        Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .wrapContentHeight()
            .clip(RoundedCornerShape(19.dp))
    ) {
        Text(
            stringResource(R.string.applications_card_info),
            Modifier.padding(15.dp),
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold
        )
    }
}