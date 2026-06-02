package com.olimhousestudio.qooraan.data.repositories

import com.olimhousestudio.qooraan.data.datasource.local.QoranDao
import com.olimhousestudio.qooraan.domain.repositories.QuranRepository

class QuranRepositoryImpl(
    private val qoranDao: QoranDao
) : QuranRepository {

    override fun getSurahs() = qoranDao.getSurah()

    override fun getPages() = qoranDao.getPage()

    override fun getJuz() = qoranDao.getJuz()

    override fun getAyatBySurah(surahNumber: Int) =
        qoranDao.getAyatSurah(surahNumber)

    override fun getAyatByJuz(juzNumber: Int) =
        qoranDao.getAyatJozz(juzNumber)

    override fun getAyatByPage(pageNumber: Int) =
        qoranDao.getAyatPage(pageNumber)

    override fun searchSurah(query: String) =
        qoranDao.getSurahBySearch(query)

    override fun searchAyat(query: String) =
        qoranDao.getAyatBySearch(query)
}