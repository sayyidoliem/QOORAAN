package com.olimhousestudio.qooraan.domain.model.quran

data class PageDomain(
    val page: Int,
    val surahNameEn: String,
    val surahNameAr: String,
    val surahNumber: Int
)