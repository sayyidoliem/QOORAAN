package com.olimhousestudio.qooraan.domain.repository

import com.olimhousestudio.qooraan.domain.model.quran.AyatDomain
import com.olimhousestudio.qooraan.domain.model.quran.AyatSearchDomain
import com.olimhousestudio.qooraan.domain.model.quran.JuzDomain
import com.olimhousestudio.qooraan.domain.model.quran.PageDomain
import com.olimhousestudio.qooraan.domain.model.quran.SurahDomain
import com.olimhousestudio.qooraan.domain.model.quran.SurahSearchDomain
import kotlinx.coroutines.flow.Flow

interface QuranRepository {

    fun getAllQuranAyah(): Flow<List<AyatDomain>>

    fun getAyatBySurah(surahNumber: Int): Flow<List<AyatDomain>>

    fun getAyatByJuz(juzNumber: Int): Flow<List<AyatDomain>>

    fun getAyatByPage(pageNumber: Int): Flow<List<AyatDomain>>

    fun getSurah(): Flow<List<SurahDomain>>

    fun getJuz(): Flow<List<JuzDomain>>

    fun getPage(): Flow<List<PageDomain>>

    fun searchSurah(query: String): Flow<List<SurahSearchDomain>>

    fun searchAyat(query: String): Flow<List<AyatSearchDomain>>
}