package com.olimhousestudio.qooraan.presentation.view.ayat

import com.olimhousestudio.qooraan.domain.model.quran.AyatDomain

sealed interface AyatEvent {

    data class LoadAyat(
        val surahNumber: Int,
        val juzNumber: Int,
        val pageNumber: Int
    ) : AyatEvent

    data class SearchQueryChanged(
        val query: String
    ) : AyatEvent

    data object Search : AyatEvent

    data object ClearSearch : AyatEvent

    data class SearchActiveChanged(
        val isActive: Boolean
    ) : AyatEvent

    data class ToggleAyahBookmark(
        val ayat: AyatDomain
    ) : AyatEvent

    data class ShowFootnote(
        val footnote: String
    ) : AyatEvent

    data object HideFootnote : AyatEvent

    data class UpdateLastRead(
        val ayat: AyatDomain,
        val index: Int
    ) : AyatEvent
}