package ru.n00byara.notificationcode.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.n00byara.notificationcode.R

data class PermissionCardModel(
    val openSettings: () -> Unit
)

@Composable
fun PermissionCard(permissionCardModel: PermissionCardModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                permissionCardModel.openSettings()
            }
            .padding(start = 10.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp),
//                .height(50.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.access_notification_listener),
                fontSize = 19.sp
            )
        }
    }
}