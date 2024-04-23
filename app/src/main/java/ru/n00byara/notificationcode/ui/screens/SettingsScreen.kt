package ru.n00byara.notificationcode.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.n00byara.notificationcode.Constants
import ru.n00byara.notificationcode.R
import ru.n00byara.notificationcode.ui.components.switcher.Switcher
import ru.n00byara.notificationcode.ui.components.switcher.SwitcherModel
import ru.n00byara.notificationcode.ui.viewmodels.SettingsScreenViewModel
import kotlin.reflect.KFunction1

@Composable
fun SettingsScreen(
    settingsScreenViewModel: SettingsScreenViewModel = viewModel(),
    setTopBarTitle: KFunction1<String, Unit>,
) {
    setTopBarTitle(stringResource(R.string.screen_settings_title))

    LazyColumn {
        if (settingsScreenViewModel.isRoot) {
            item {
                LsposedInfoCard(settingsScreenViewModel.isActive)
            }
        }

        itemsIndexed(
            listOf(
                SwitcherModel(
                    R.string.switcher_code,
                    Constants.SWITCH_CODE,
                    settingsScreenViewModel.getBoolean(Constants.SWITCH_CODE),
                    settingsScreenViewModel::setBoolean,
                    settingsScreenViewModel.isActive,
                    settingsScreenViewModel.isRoot
                ),
                SwitcherModel(
                    R.string.switcher_phone,
                    Constants.SWITCH_PHONE,
                    settingsScreenViewModel.getBoolean(Constants.SWITCH_PHONE),
                    settingsScreenViewModel::setBoolean,
                    settingsScreenViewModel.isActive,
                    settingsScreenViewModel.isRoot
                ),
                SwitcherModel(
                    R.string.switcher_shazam,
                    Constants.APPLICATION_PREF + Constants.SHAZAM_PACKAGE,
                    settingsScreenViewModel.getBoolean(Constants.APPLICATION_PREF + Constants.SHAZAM_PACKAGE),
                    settingsScreenViewModel::setBoolean,
                    settingsScreenViewModel.isActive,
                    settingsScreenViewModel.isRoot
                ),
                SwitcherModel(
                    R.string.switcher_track_numbers,
                    Constants.SWITHC_TRACK_NUMBER,
                    settingsScreenViewModel.getBoolean(Constants.SWITHC_TRACK_NUMBER),
                    settingsScreenViewModel::setBoolean,
                    settingsScreenViewModel.isActive,
                    settingsScreenViewModel.isRoot
                )
            )
        ) { _, item ->
            Switcher(item)
        }
    }
}

@Composable
fun LsposedInfoCard(isActive: Boolean) {
    Card(
        Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .height(125.dp)
            .clip(RoundedCornerShape(19.dp)),
        colors = CardDefaults.cardColors(
            containerColor = if (isActive) MaterialTheme.colorScheme.inverseOnSurface else MaterialTheme.colorScheme.tertiaryContainer
        )
    ) {
        Row(
            Modifier.padding(10.dp)
        ) {
            Column(
                Modifier.padding(top = 29.dp, bottom = 29.dp, start = 10.dp, end = 10.dp)
            ) {
                Icon(
                    Icons.Filled.Info,
                    null,
                    Modifier.size(47.dp)
                )
            }

            Column(
                Modifier.padding(start = 5.dp)
            ) {
                Text(
                    stringResource(if (isActive) R.string.module_status_active else R.string.module_status_inactive),
                    Modifier.padding(top = 14.5.dp),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    stringResource(if (isActive) R.string.module_info_active else R.string.module_info_inactive),
                    Modifier.padding(top = 5.dp),
                    fontSize = 15.sp
                )
            }
        }
    }
}