package com.olimhousestudio.qooraan.domain.usecase.lastread

import com.olimhousestudio.qooraan.domain.repository.LastReadRepository
import javax.inject.Inject

class ObserveLastReadUseCase @Inject constructor(
    private val repository: LastReadRepository
) {
    operator fun invoke() = repository.lastReadFlow
}