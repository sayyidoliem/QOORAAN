package com.olimhousestudio.qooraan.domain.model.quran

data class JuzDomain(
    val juzNumber: Int,
    val surahNumber: Int,
    val surahNameEn: String,
    val surahNameAr: String,
    val ayahNumber: Int
)