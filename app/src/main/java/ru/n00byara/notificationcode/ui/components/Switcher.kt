package ru.n00byara.notificationcode.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class SwitcherModel(
    val title: Int,
    val prefName: String,
    val state: Boolean,
    val setState: (String, Boolean) -> Unit,
    val useCase: MutableState<Int>,
    val moduleActive: MutableState<Boolean>,
    val premissionAccess: MutableState<Boolean>
)

@Composable
fun Switcher(switcherModel: SwitcherModel) {
    var chechedState by remember { mutableStateOf(switcherModel.state) }

    if (
        (!switcherModel.moduleActive.value && switcherModel.useCase.value == 0) ||
        (!switcherModel.premissionAccess.value && switcherModel.useCase.value == 1)
    ) {
        chechedState = false
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .toggleable(
                value = chechedState,
                onValueChange = {
                    if (
                        (switcherModel.moduleActive.value && switcherModel.useCase.value == 0) ||
                        (switcherModel.premissionAccess.value && switcherModel.useCase.value == 1)
                    ) {
                        chechedState = it
                        switcherModel.setState(switcherModel.prefName, it)
                    }
                }
            )
            .padding(start = 10.dp, end = 15.dp, top = 7.dp, bottom = 7.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = stringResource(switcherModel.title),
                fontSize = 19.sp
            )
            Switch(
                checked = chechedState,
                onCheckedChange = null
            )
        }
    }
}