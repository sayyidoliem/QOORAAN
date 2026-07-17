package com.olimhousestudio.qooraan.data.repositories

import com.olimhousestudio.qooraan.data.datasource.local.QoranDao
import com.olimhousestudio.qooraan.data.mapper.toDomain
import com.olimhousestudio.qooraan.domain.model.quran.AyatDomain
import com.olimhousestudio.qooraan.domain.model.quran.AyatSearchDomain
import com.olimhousestudio.qooraan.domain.model.quran.JuzDomain
import com.olimhousestudio.qooraan.domain.model.quran.PageDomain
import com.olimhousestudio.qooraan.domain.model.quran.SurahDomain
import com.olimhousestudio.qooraan.domain.model.quran.SurahSearchDomain
import com.olimhousestudio.qooraan.domain.repository.QuranRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class QuranRepositoryImpl @Inject constructor(
    private val qoranDao: QoranDao
) : QuranRepository {

    override fun getAllQuranAyah(): Flow<List<AyatDomain>> {
        return qoranDao.getAllQoranAyah().map { list ->
            list.map { it.toDomain() }
        }
    }

    override fun getAyatBySurah(surahNumber: Int): Flow<List<AyatDomain>> {
        return qoranDao.getAyatSurah(surahNumber).map { list ->
            list.map { it.toDomain() }
        }
    }

    override fun getAyatByJuz(juzNumber: Int): Flow<List<AyatDomain>> {
        return qoranDao.getAyatJozz(juzNumber).map { list ->
            list.map { it.toDomain() }
        }
    }

    override fun getAyatByPage(pageNumber: Int): Flow<List<AyatDomain>> {
        return qoranDao.getAyatPage(pageNumber).map { list ->
            list.map { it.toDomain() }
        }
    }

    override fun getSurah(): Flow<List<SurahDomain>> {
        return qoranDao.getSurah().map { list ->
            list.map { it.toDomain() }
        }
    }

    override fun getJuz(): Flow<List<JuzDomain>> {
        return qoranDao.getJuz().map { list ->
            list.map { it.toDomain() }
        }
    }

    override fun getPage(): Flow<List<PageDomain>> {
        return qoranDao.getPage().map { list ->
            list.map { it.toDomain() }
        }
    }

    override fun searchSurah(query: String): Flow<List<SurahSearchDomain>> {
        return qoranDao.getSurahBySearch(query).map { list ->
            list.map { it.toDomain() }
        }
    }

    override fun searchAyat(query: String): Flow<List<AyatSearchDomain>> {
        return qoranDao.getAyatBySearch(query).map { list ->
            list.map { it.toDomain() }
        }
    }
}