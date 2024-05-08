package ru.n00byara.notificationcode.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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

data class UseCaseAlertDialogModel(
    val openDialogState: MutableState<Boolean>,
    val setSelectItem: ((Int) -> Unit)?,
    val selectedCaseIndex: Int = 0
)

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

        Row(
            modifier = Modifier
                .wrapContentHeight()
                .clip(RoundedCornerShape(19.dp))
                .background(MaterialTheme.colorScheme.primaryContainer)
        ) {
            Column {
                Row(
                    modifier = Modifier
                        .padding(start = 15.dp, end = 15.dp, top = 15.dp, bottom = 7.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(R.string.alert_dialog_use_case),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                val (selectedOption, onOptionSelected) = remember { mutableStateOf(cases[useCaseAlertDialogModel.selectedCaseIndex]) }

                cases.forEachIndexed { index, case ->
                    UseCaseCheckBox(
                        case = case,
                        selectedOption = selectedOption,
                        onOptionSelected = onOptionSelected,
                        setSelectItem = useCaseAlertDialogModel.setSelectItem,
                        index = index
                    )
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
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .clickable {
                onOptionSelected(case)
                setSelectItem?.invoke(index)
            }
            .padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton (
            selected = (case == selectedOption),
            onClick = null
        )
        Text(
            text = case
        )
    }
}