package com.olimhousestudio.qooraan.presentation.view.ayat.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FootnoteBottomSheet(
    footnote: String,
    onDismiss: () -> Unit,
    onShare: (String) -> Unit
) {
    val sheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = onDismiss
    ) {
        LazyColumn {
            item {
                Text(
                    text = "Footnote",
                    modifier = Modifier.padding(16.dp)
                )

                Text(
                    text = footnote,
                    modifier = Modifier.padding(16.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    OutlinedButton(
                        onClick = {
                            onShare(footnote)
                        },
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(text = "Share")
                    }

                    Button(
                        onClick = onDismiss,
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(text = "Close")
                    }
                }
            }
        }
    }
}