package com.olimhousestudio.qooraan.domain.model.quran

data class AyatSearchDomain(
    val id: Int,
    val surahNumber: Int,
    val ayatTextEmlay: String,
    val surahNameEmlaey: String,
    val ayatText: String,
    val translateEn: String,
    val translateId: String,
    val ayatNumber: Int,
    val numberOfAyah: Int,
    val surahDescendPlace: String
)