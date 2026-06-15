package com.olimhousestudio.qooraan.domain.model.quran

data class SurahDomain(
    val id: Int,
    val surahNumber: Int,
    val surahNameAr: String,
    val juzNumber: Int,
    val surahNameEn: String,
    val numberOfAyah: Int,
    val surahDescendPlace: String,
    val surahNameId: String
)