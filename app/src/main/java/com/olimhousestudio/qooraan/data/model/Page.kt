package com.olimhousestudio.qooraan.data.model

import androidx.room.ColumnInfo
import androidx.room.DatabaseView

@DatabaseView("SELECT DISTINCT sora_name_en,sora_name_ar,sora, page FROM quran GROUP by page")
data class Page(
    @ColumnInfo(name = "page") val page: Int = 0,
    @ColumnInfo(name = "sora_name_en") val surahNameEn: String = "",
    @ColumnInfo(name = "sora_name_ar") val surahNameAr: String? = "",
    @ColumnInfo(name = "sora") val surahNumber: Int? = 0,
)