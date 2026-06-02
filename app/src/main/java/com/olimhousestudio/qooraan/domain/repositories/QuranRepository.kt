package com.olimhousestudio.qooraan.domain.repositories

import com.olimhousestudio.qooraan.data.datasource.local.entities.Qoran
import com.olimhousestudio.qooraan.data.model.AyatSearch
import com.olimhousestudio.qooraan.data.model.Juz
import com.olimhousestudio.qooraan.data.model.Page
import com.olimhousestudio.qooraan.data.model.Surah
import com.olimhousestudio.qooraan.data.model.SurahSearch
import kotlinx.coroutines.flow.Flow

interface QuranRepository {
    fun getSurahs(): Flow<List<Surah>>
    fun getPages(): Flow<List<Page>>
    fun getJuz(): Flow<List<Juz>>
    fun getAyatBySurah(surahNumber: Int): Flow<List<Qoran>>
    fun getAyatByJuz(juzNumber: Int): Flow<List<Qoran>>
    fun getAyatByPage(pageNumber: Int): Flow<List<Qoran>>
    fun searchSurah(query: String): Flow<List<SurahSearch>>
    fun searchAyat(query: String): Flow<List<AyatSearch>>
}