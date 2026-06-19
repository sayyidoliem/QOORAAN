package com.olimhousestudio.qooraan.domain.model

data class LastRead(
    val surahNumber: Int = -1,
    val surahName: String = "",
    val ayahNumber: Int = -1,
    val lastDate: Long = -1L,
    val index: Int = -1
)