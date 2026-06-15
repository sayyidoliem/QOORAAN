package com.olimhousestudio.qooraan.domain.model.quran

data class SurahSearchDomain(
    val id: Int,
    val surahNumber: Int,
    val surahNameEmlay: String,
    val ayatNameEmlay: String,
    val juzNumber: Int,
    val numberOfAyah: Int,
    val surahDescendPlace: String
)