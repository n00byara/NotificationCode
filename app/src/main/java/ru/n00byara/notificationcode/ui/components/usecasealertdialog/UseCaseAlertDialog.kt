package ru.n00byara.notificationcode.ui.components.usecasealertdialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.n00byara.notificationcode.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UseCaseAlertDialog(useCaseAlertDialogModel: UseCaseAlertDialogModel) {
    AlertDialog(
        onDismissRequest = {
            useCaseAlertDialogModel.openDialogState.value = false
        },
    ) {
        val cases = listOf(
            "${stringResource(R.string.alert_dialog_use_case_root)} ${stringResource(R.string.alert_dialog_use_case_recommended)}",
            stringResource(R.string.alert_dialog_use_case_non_root)
        )

        Card(
            modifier = Modifier
                .wrapContentHeight()
                .clip(RoundedCornerShape(19.dp))
        ) {
            Column(
                modifier = Modifier.padding(10.dp)
            ) {
                Row(
                    modifier = Modifier
                        .padding(7.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(R.string.alert_dialog_use_case),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                val (selectedOption, onOptionSelected) = remember { mutableStateOf(cases[useCaseAlertDialogModel.selectedCaseIndex]) }

                Column(
                    modifier = Modifier.selectableGroup()
                ) {
                    cases.forEachIndexed { index, case ->
                        UseCaseCheckBox(case, selectedOption, onOptionSelected, useCaseAlertDialogModel.setSelectItem, index)
                    }
                }
            }
        }
    }
}

@Composable
fun UseCaseCheckBox(
    case: String,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
    setSelectItem: ((Int) -> Unit)?,
    index: Int
) {
    Row(
        modifier = Modifier.padding(7.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton (
            (case == selectedOption),
            {
                onOptionSelected(case)
                setSelectItem?.invoke(index)
            }
        )
        Text(
            text = case
        )
    }
}