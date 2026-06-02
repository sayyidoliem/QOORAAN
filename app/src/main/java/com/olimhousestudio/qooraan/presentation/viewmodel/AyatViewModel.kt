package com.olimhousestudio.qooraan.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.olimhousestudio.qooraan.domain.repositories.QuranRepository
import com.olimhousestudio.qooraan.domain.repositories.SettingsRepository
import com.olimhousestudio.qooraan.presentation.view.ayat.AyatEvent
import com.olimhousestudio.qooraan.presentation.view.ayat.AyatUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AyatViewModel(
    private val quranRepository: QuranRepository,
    private val bookmarkRepository: BookmarkRepository,
    private val settingsRepository: SettingsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AyatUiState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: AyatEvent) {
        when (event) {
            is AyatEvent.BookmarkAyat -> bookmarkAyat(event)
            is AyatEvent.Search -> search(event.query)
            is AyatEvent.PlayAyat -> playAyat(event)
            is AyatEvent.ChangeLanguage -> changeLanguage(event.language)
        }
    }
}