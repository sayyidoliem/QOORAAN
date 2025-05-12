package com.olimhousestudio.qooraan.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ItemCardPrayer(
    modifier: Modifier = Modifier,
    titleSholat: String,
    timesholat: String
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer
        )
    ) {
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Text(
                text = timesholat,
                modifier = Modifier.padding(12.dp),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = titleSholat,
                modifier = Modifier.padding(12.dp),
                fontWeight = FontWeight.Bold
            )
        }

    }
}