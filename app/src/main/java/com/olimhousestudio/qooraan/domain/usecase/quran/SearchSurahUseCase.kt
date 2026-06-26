package com.olimhousestudio.qooraan.domain.usecase.quran

import com.olimhousestudio.qooraan.domain.repository.QuranRepository
import javax.inject.Inject

class SearchSurahUseCase @Inject constructor(
    private val repository: QuranRepository
) {
    operator fun invoke(query: String) = repository.searchSurah(query)
}