package com.olimhousestudio.qooraan.data.datasource.local.preference

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.olimhousestudio.qooraan.domain.model.LastRead
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.lastReadDataStore by preferencesDataStore(
    name = "qooraan_last_read_preferences"
)

class LastReadPreferencesDataSource(
    private val context: Context
) {

    val lastReadFlow: Flow<LastRead> =
        context.lastReadDataStore.data.map { preferences ->
            LastRead(
                surahNumber = preferences[LAST_SURAH_NUMBER] ?: -1,
                surahName = preferences[LAST_SURAH_NAME].orEmpty(),
                ayahNumber = preferences[LAST_AYAH_NUMBER] ?: -1,
                lastDate = preferences[LAST_DATE] ?: -1L,
                index = preferences[LAST_INDEX] ?: -1
            )
        }

    suspend fun updateLastRead(lastRead: LastRead) {
        context.lastReadDataStore.edit { preferences ->
            preferences[LAST_SURAH_NUMBER] = lastRead.surahNumber
            preferences[LAST_SURAH_NAME] = lastRead.surahName
            preferences[LAST_AYAH_NUMBER] = lastRead.ayahNumber
            preferences[LAST_DATE] = lastRead.lastDate
            preferences[LAST_INDEX] = lastRead.index
        }
    }

    suspend fun clearLastRead() {
        context.lastReadDataStore.edit { preferences ->
            preferences.clear()
        }
    }

    private companion object {
        val LAST_SURAH_NUMBER = intPreferencesKey("last_surah_number")
        val LAST_SURAH_NAME = stringPreferencesKey("last_surah_name")
        val LAST_AYAH_NUMBER = intPreferencesKey("last_ayah_number")
        val LAST_DATE = longPreferencesKey("last_date")
        val LAST_INDEX = intPreferencesKey("last_index")
    }
}