package ru.n00byara.notificationcode.ui.screens

import androidx.compose.foundation.background
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
import ru.n00byara.notificationcode.Constants
import ru.n00byara.notificationcode.R
import ru.n00byara.notificationcode.ui.components.Switcher
import ru.n00byara.notificationcode.ui.components.SwitcherModel
import ru.n00byara.notificationcode.ui.viewmodels.SettingsScreenViewModel
import kotlin.reflect.KFunction1

@Composable
fun SettingsScreen(
    settingsScreenViewModel: SettingsScreenViewModel,
    setTopBarTitle: KFunction1<String, Unit>,
) {
    setTopBarTitle(stringResource(R.string.screen_settings_title))

    LazyColumn {
        item {
            LsposedInfoCard(isActive = settingsScreenViewModel.moduleActive)
        }
        itemsIndexed(
            listOf(
                SwitcherModel(
                    title = R.string.switcher_code,
                    prefName = Constants.SWITCH_CODE,
                    state = settingsScreenViewModel.getBoolean(Constants.SWITCH_CODE),
                    setState = settingsScreenViewModel::setBoolean,
                    moduleActive = settingsScreenViewModel.moduleActive
                ),
                SwitcherModel(
                    title = R.string.switcher_phone,
                    prefName = Constants.SWITCH_PHONE,
                    state = settingsScreenViewModel.getBoolean(Constants.SWITCH_PHONE),
                    setState = settingsScreenViewModel::setBoolean,
                    moduleActive = settingsScreenViewModel.moduleActive,
                ),
                SwitcherModel(
                    title = R.string.switcher_shazam,
                    prefName = Constants.APPLICATION_PREF + Constants.SHAZAM_PACKAGE,
                    state = settingsScreenViewModel.getBoolean(Constants.APPLICATION_PREF + Constants.SHAZAM_PACKAGE),
                    setState = settingsScreenViewModel::setBoolean,
                    moduleActive = settingsScreenViewModel.moduleActive,
                ),
                SwitcherModel(
                    title = R.string.switcher_track_numbers,
                    prefName = Constants.SWITHC_TRACK_NUMBER,
                    state = settingsScreenViewModel.getBoolean(Constants.SWITHC_TRACK_NUMBER),
                    setState = settingsScreenViewModel::setBoolean,
                    moduleActive = settingsScreenViewModel.moduleActive,
                )
            )
        ) { _, item ->
            Switcher(switcherModel = item)
        }
    }
}

@Composable
fun LsposedInfoCard(isActive: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .height(125.dp)
            .clip(RoundedCornerShape(19.dp))
            .background(
                if (isActive) MaterialTheme.colorScheme.inverseOnSurface
                else MaterialTheme.colorScheme.tertiaryContainer
            ),

    ) {
        Row(
            modifier = Modifier.padding(10.dp)
        ) {
            Column(
                modifier = Modifier.padding(top = 29.dp, bottom = 29.dp, start = 10.dp, end = 10.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Info,
                    contentDescription = null,
                    modifier = Modifier.size(47.dp)
                )
            }

            Column(
                modifier = Modifier.padding(start = 5.dp)
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