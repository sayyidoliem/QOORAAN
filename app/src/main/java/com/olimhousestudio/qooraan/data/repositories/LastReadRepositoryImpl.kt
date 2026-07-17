package com.olimhousestudio.qooraan.data.repositories

import com.olimhousestudio.qooraan.data.datasource.local.preference.LastReadPreferencesDataSource
import com.olimhousestudio.qooraan.domain.model.LastRead
import com.olimhousestudio.qooraan.domain.repository.LastReadRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LastReadRepositoryImpl @Inject constructor(
    private val dataSource: LastReadPreferencesDataSource
) : LastReadRepository {

    override val lastReadFlow: Flow<LastRead>
        get() = dataSource.lastReadFlow

    override suspend fun updateLastRead(lastRead: LastRead) {
        dataSource.updateLastRead(lastRead)
    }

    override suspend fun clearLastRead() {
        dataSource.clearLastRead()
    }
}