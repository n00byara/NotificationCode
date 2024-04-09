package ru.n00byara.notificationcode.ui.components.switcher

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Switcher(switcherModel: SwitcherModel) {
    var chechedState = remember {
        mutableStateOf(switcherModel.state)
    }

    if (!switcherModel.moduleActive) chechedState.value = false

    Card(
        Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, end = 15.dp, top = 7.dp, bottom = 7.dp)
            .height(50.dp)
            .clip(RoundedCornerShape(19.dp))
    ) {
        Row(
            Modifier
                .fillMaxSize()
                .padding(10.dp),
            Arrangement.SpaceBetween,
            Alignment.CenterVertically,
        ) {
            Text(
                stringResource(switcherModel.title),
                fontSize = 19.sp
            )
            Switch(
                chechedState.value,
                {
                    if (switcherModel.moduleActive) {
                        chechedState.value = it
                        switcherModel.setState(switcherModel.prefName, it)
                    }
                }
            )
        }
    }
}