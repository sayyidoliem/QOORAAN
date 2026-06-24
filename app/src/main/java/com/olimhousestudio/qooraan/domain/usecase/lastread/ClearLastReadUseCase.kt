package com.olimhousestudio.qooraan.domain.usecase.lastread

import com.olimhousestudio.qooraan.domain.repository.LastReadRepository
import javax.inject.Inject

class ClearLastReadUseCase @Inject constructor(
    private val repository: LastReadRepository
) {
    suspend operator fun invoke() {
        repository.clearLastRead()
    }
}