package com.olimhousestudio.qooraan.domain.usecase.quran

import com.olimhousestudio.qooraan.domain.repository.QuranRepository
import javax.inject.Inject

class GetAyatByPageUseCase @Inject constructor(
    private val repository: QuranRepository
) {
    operator fun invoke(pageNumber: Int) = repository.getAyatByPage(pageNumber)
}