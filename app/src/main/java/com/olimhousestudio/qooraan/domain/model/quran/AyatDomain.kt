package com.olimhousestudio.qooraan.domain.model.quran

data class AyatDomain(
    val id: Int,
    val juzNumber: Int,
    val surahNumber: Int,
    val surahNameEn: String,
    val surahNameAr: String,
    val page: Int,
    val ayatNumber: Int,
    val ayatText: String,
    val ayatTextEmlaey: String,
    val translateId: String,
    val footnotesId: String,
    val surahNameId: String,
    val surahDescendPlace: String,
    val surahNameEmlaey: String,
    val translateEn: String,
    val footnotesEn: String
)