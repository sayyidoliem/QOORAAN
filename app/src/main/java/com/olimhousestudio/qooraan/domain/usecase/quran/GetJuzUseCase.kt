package com.olimhousestudio.qooraan.domain.usecase.quran

import com.olimhousestudio.qooraan.domain.repository.QuranRepository
import javax.inject.Inject

class GetJuzUseCase @Inject constructor(
    private val repository: QuranRepository
) {
    operator fun invoke() = repository.getJuz()
}