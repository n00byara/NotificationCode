package ru.n00byara.notificationcode.ui.components.applicationinfocheckbox

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.google.accompanist.drawablepainter.rememberDrawablePainter
import ru.n00byara.notificationcode.Constants

@Composable
fun ApplicationInfoCheckBox(
    applicationInfoCheckBoxModel: ApplicationInfoCheckBoxModel
) {
    val checkedState = remember {
        mutableStateOf(applicationInfoCheckBoxModel.checked)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, end = 15.dp, top = 7.dp, bottom = 7.dp)
            .clip(RoundedCornerShape(19.dp))
            .clickable {
                checkedState.value = !checkedState.value
                applicationInfoCheckBoxModel.setState(
                    Constants.APPLICATION_PREF + applicationInfoCheckBoxModel.appInfo.packageName,
                    checkedState.value
                )
            }
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                Modifier
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    rememberDrawablePainter(applicationInfoCheckBoxModel.appInfo.icon),
                    null,
                    Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                )
                Text(
                    applicationInfoCheckBoxModel.appInfo.name,
                    Modifier.padding(start = 5.dp)
                )
            }
            Row(
                Modifier
                    .padding(7.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Icon(
                    if (checkedState.value) Icons.Default.CheckCircle else Icons.Outlined.CheckCircle,
                    applicationInfoCheckBoxModel.appInfo.name,
                    Modifier
                        .size(30.dp)
                )
            }
        }
    }
}