package com.olimhousestudio.qooraan.presentation.view.ayat

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.olimhousestudio.qooraan.presentation.viewmodel.AyatViewModel

@Composable
fun AyatRoute(
    goBack: () -> Unit,
    surahNumber: Int,
    juzNumber: Int,
    pageNumber: Int,
    index: Int,
    viewModel: AyatViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    AyatScreen(
        uiState = uiState,
        goBack = goBack,
        onEvent = viewModel::onEvent
    )
}