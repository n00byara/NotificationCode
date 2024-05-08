package ru.n00byara.notificationcode.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.n00byara.notificationcode.R

data class PermissionAlertDialogModel(
    val openDialogState: MutableState<Boolean>,
    val openSettings: () -> Unit
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PermissionAlertDialog(permissionAlertDialogModel: PermissionAlertDialogModel) {
    AlertDialog(
        onDismissRequest = {
            permissionAlertDialogModel.openDialogState.value = false
        },
    ) {
        Row(
            modifier = Modifier
                .wrapContentHeight()
                .clip(RoundedCornerShape(19.dp))
        ) {
            Column(
                modifier = Modifier
                    .padding(10.dp)
            ) {
                Row(
                    modifier = Modifier.padding(start = 7.dp, end = 7.dp, top = 7.dp, bottom = 15.dp)
                ) {
                    Text(
                        text = stringResource(R.string.alert_dialog_permisson_firs_launch_text),
                        fontSize = 20.sp
                    )
                }

                Row(
                    modifier = Modifier
                        .padding(start = 20.dp, end = 20.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                        ),
                        onClick = {
                            permissionAlertDialogModel.openSettings()
                            permissionAlertDialogModel.openDialogState.value = false
                        }
                    ) {
                        Text(
                            text = stringResource(R.string.alert_dealog_permission_first_launch_ok)
                        )
                    }

                    Button(
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                        ),
                        onClick = { permissionAlertDialogModel.openDialogState.value = false }
                    ) {
                        Text(
                            text = stringResource(R.string.alert_dealog_permission_first_launch_cancel)
                        )
                    }
                }
            }
        }
    }
}