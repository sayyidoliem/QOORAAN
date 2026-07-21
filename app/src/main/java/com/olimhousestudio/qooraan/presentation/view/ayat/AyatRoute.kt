package com.olimhousestudio.qooraan.presentation.view.ayat

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.olimhousestudio.qooraan.presentation.viewmodel.ayat.AyatViewModel

@Composable
fun AyatRoute(
    goBack: () -> Unit,
    surahNumber: Int,
    juzNumber: Int,
    pageNumber: Int,
    index: Int
) {
    val viewModel: AyatViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(
        surahNumber,
        juzNumber,
        pageNumber
    ) {
        viewModel.onEvent(
            AyatEvent.LoadAyat(
                surahNumber = surahNumber,
                juzNumber = juzNumber,
                pageNumber = pageNumber
            )
        )
    }

    AyatScreen(
        uiState = uiState,
        initialScrollIndex = index,
        goBack = goBack,
        onEvent = viewModel::onEvent
    )
}
