package ru.n00byara.notificationcode.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.google.accompanist.drawablepainter.rememberDrawablePainter
import ru.n00byara.notificationcode.Constants
import ru.n00byara.notificationcode.application.InstalledAppInfo

data class ApplicationInfoSwitchModel(
    val appInfo: InstalledAppInfo,
    val checked: Boolean,
    val setState: (String, Boolean) -> Unit
)

@Composable
fun ApplicationInfoCheckBox(
    applicationInfoSwitchModel: ApplicationInfoSwitchModel
) {
    var checkedState by remember { mutableStateOf(applicationInfoSwitchModel.checked) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .toggleable(
                value = checkedState,
                onValueChange = {
                    checkedState = it
                    applicationInfoSwitchModel.setState(
                        Constants.APPLICATION_PREF + applicationInfoSwitchModel.appInfo.packageName,
                        checkedState
                    )
                }
            )
            .padding(start = 10.dp, end = 15.dp, top = 7.dp, bottom = 7.dp)

    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = rememberDrawablePainter(applicationInfoSwitchModel.appInfo.icon),
                    contentDescription = applicationInfoSwitchModel.appInfo.name,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                )
                Text(
                    text = applicationInfoSwitchModel.appInfo.name,
                    modifier = Modifier.padding(start = 10.dp)
                )
            }

            Row(
                modifier = Modifier
                    .padding(7.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Switch(
                    checked = checkedState,
                    onCheckedChange = null
                )
            }
        }
    }
}