package com.olimhousestudio.qooraan.domain.usecase.lastread

import com.olimhousestudio.qooraan.domain.model.LastRead
import com.olimhousestudio.qooraan.domain.repository.LastReadRepository
import javax.inject.Inject

class UpdateLastReadUseCase @Inject constructor(
    private val repository: LastReadRepository
) {
    suspend operator fun invoke(lastRead: LastRead) {
        repository.updateLastRead(lastRead)
    }
}