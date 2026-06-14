package com.olimhousestudio.qooraan.domain.model.bookmark

data class AyahBookmarkDomain(
    val id: Int? = null,
    val surahName: String,
    val ayahNumber: Int,
    val surahNumber: Int,
    val createdAt: Long
)