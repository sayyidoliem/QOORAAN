package com.olimhousestudio.qooraan.domain.usecase.quran

import com.olimhousestudio.qooraan.domain.repository.QuranRepository
import javax.inject.Inject

class GetAyatByJuzUseCase @Inject constructor(
    private val repository: QuranRepository
) {
    operator fun invoke(juzNumber: Int) = repository.getAyatByJuz(juzNumber)
}