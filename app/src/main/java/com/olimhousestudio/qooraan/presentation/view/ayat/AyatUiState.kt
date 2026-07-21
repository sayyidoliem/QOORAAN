package com.olimhousestudio.qooraan.presentation.view.ayat

import com.olimhousestudio.qooraan.common.Qories
import com.olimhousestudio.qooraan.domain.model.AppSettings
import com.olimhousestudio.qooraan.domain.model.quran.AyatDomain
import com.olimhousestudio.qooraan.domain.model.quran.AyatSearchDomain

data class AyatUiState(
    val isLoading: Boolean = true,
    val ayatList: List<AyatDomain> = emptyList(),

    val searchQuery: String = "",
    val searchResults: List<AyatSearchDomain> = emptyList(),
    val isSearchActive: Boolean = false,

    val bookmarkedAyahKeys: Set<String> = emptySet(),

    val selectedFootnote: String = "",
    val isFootnoteSheetVisible: Boolean = false,

    val selectedLanguage: Int = AppSettings.INDONESIA,
    val isVisibleTranslate: Boolean = false,
    val selectedQori: Qories = Qories.ABDUL_SOMAD,
    val ayahTextSize: Float = 32f,

    val errorMessage: String? = null
) {
    val surahNameEn: String
        get() = ayatList.firstOrNull()?.surahNameEn.orEmpty()

    val surahNameAr: String
        get() = ayatList.firstOrNull()?.surahNameAr.orEmpty()

    val surahNameId: String
        get() = ayatList.firstOrNull()?.surahNameId.orEmpty()

    val juzNumber: Int
        get() = ayatList.firstOrNull()?.juzNumber ?: 0

    val descendPlace: String
        get() = ayatList.firstOrNull()?.surahDescendPlace.orEmpty()

    val totalAyah: Int
        get() = ayatList.size
}

fun ayahBookmarkKey(
    surahNumber: Int?,
    ayahNumber: Int?
): String {
    return "${surahNumber ?: -1}:${ayahNumber ?: -1}"
}