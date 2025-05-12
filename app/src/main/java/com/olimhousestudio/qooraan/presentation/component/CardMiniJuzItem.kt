package com.olimhousestudio.qooraan.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JuzCardMiniItem(
    surahList: List<String?>,
    surahNumberList: List<Int?>,
    onItemClick: (Int?) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        if (surahList.isNotEmpty() && surahNumberList.isNotEmpty()) {
            for (index in surahList.indices) {
                Card(
                    modifier = Modifier.padding(8.dp),
                    onClick = { onItemClick(surahNumberList[index]) }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "${surahNumberList[index]}.",
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "${surahList[index]}",
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )

                    }
                }
            }
        }
    }
}