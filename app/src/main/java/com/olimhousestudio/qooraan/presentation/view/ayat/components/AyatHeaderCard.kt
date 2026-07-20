package com.olimhousestudio.qooraan.presentation.view.ayat.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.olimhousestudio.qooraan.presentation.view.ayat.AyatUiState

@Composable
fun AyatHeaderCard(
    uiState: AyatUiState,
    modifier: Modifier = Modifier
) {
    val bismillahText = when (uiState.ayatList.firstOrNull()?.surahNumber) {
        1, 9 -> "أَعُوذُ بِاللَّهِ مِنَ الشَّيْطَانِ الرَّجِيمِ"
        else -> "بِسْمِ اللهِ الرَّحْمَنِ الرَّحِيْمِ"
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = uiState.surahNameEn,
                color = MaterialTheme.colorScheme.onPrimary,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )

            Row(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(8.dp)
            ) {
                Text(
                    text = uiState.surahNameAr,
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontWeight = FontWeight.Medium,
                    fontSize = 19.sp
                )

                Spacer(modifier = Modifier.padding(4.dp))

                Text(
                    text = "·",
                    color = MaterialTheme.colorScheme.onPrimary
                )

                Spacer(modifier = Modifier.padding(4.dp))

                Text(
                    text = uiState.surahNameId,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }

            HorizontalDivider(
                modifier = Modifier.padding(
                    horizontal = 56.dp,
                    vertical = 16.dp
                ),
                thickness = 2.dp,
                color = MaterialTheme.colorScheme.onPrimary
            )

            Row(
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = "Juz ${uiState.juzNumber}",
                    color = MaterialTheme.colorScheme.onPrimary
                )

                Spacer(modifier = Modifier.padding(4.dp))

                Text(
                    text = "·",
                    color = MaterialTheme.colorScheme.onPrimary
                )

                Spacer(modifier = Modifier.padding(4.dp))

                Text(
                    text = uiState.descendPlace,
                    color = MaterialTheme.colorScheme.onPrimary
                )

                Spacer(modifier = Modifier.padding(4.dp))

                Text(
                    text = "·",
                    color = MaterialTheme.colorScheme.onPrimary
                )

                Spacer(modifier = Modifier.padding(4.dp))

                Text(
                    text = "${uiState.totalAyah} Ayat",
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                text = "· $bismillahText ·",
                color = MaterialTheme.colorScheme.onPrimary,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
        }
    }
}