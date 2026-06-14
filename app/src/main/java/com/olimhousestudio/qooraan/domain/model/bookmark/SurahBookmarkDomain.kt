package com.olimhousestudio.qooraan.domain.model.bookmark

data class SurahBookmarkDomain(
    val surahNameEn: String,
    val surahNameAr: String,
    val totalAyah: Int,
    val juzNumber: Int,
    val surahDescend: String,
    val surahNumber: Int
)