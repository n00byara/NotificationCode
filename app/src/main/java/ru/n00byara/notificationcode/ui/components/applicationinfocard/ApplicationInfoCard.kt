package ru.n00byara.notificationcode.ui.components.applicationinfocard

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.drawablepainter.rememberDrawablePainter
import ru.n00byara.notificationcode.Constants

@Composable
fun ApplicationInfoCard(
   applicationInfoCardModel: ApplicationInfoCardModel
) {
    val checkedState = remember {
        mutableStateOf(applicationInfoCardModel.checked)
    }

    Card(
        modifier =Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, end = 15.dp, top = 7.dp, bottom = 7.dp)
            .clip(RoundedCornerShape(19.dp))
            .clickable {
                checkedState.value = !checkedState.value
                applicationInfoCardModel.setState(
                    Constants.APPLICATION_PREF + applicationInfoCardModel.appInfo.name,
                    checkedState.value
                )
            },
        colors = CardDefaults.cardColors(
            containerColor = if (checkedState.value) MaterialTheme.colorScheme.onPrimaryContainer else Color.Gray,
            contentColor = if (checkedState.value) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.onBackground
        ),
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                rememberDrawablePainter(applicationInfoCardModel.appInfo.icon),
                null,
                Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )
            Text(
                applicationInfoCardModel.appInfo.name,
                Modifier.padding(start = 5.dp)
            )
        }
    }
}