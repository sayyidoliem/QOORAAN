package com.olimhousestudio.qooraan.domain.repository

import com.olimhousestudio.qooraan.domain.model.LastRead
import kotlinx.coroutines.flow.Flow

interface LastReadRepository {

    val lastReadFlow: Flow<LastRead>

    suspend fun updateLastRead(lastRead: LastRead)

    suspend fun clearLastRead()
}