package com.olimhousestudio.qooraan.data.model

import androidx.room.ColumnInfo
import androidx.room.DatabaseView

//SELECT DISTINCT jozz, sora_name_en FROM quran WHERE jozz = 29 GROUP by sora_name_en
@DatabaseView("SELECT id, jozz, sora, aya_text, sora_name_en,sora_name_ar, aya_no FROM quran GROUP by sora, jozz ORDER BY id ASC")
data class Juz(
    @ColumnInfo(name = "jozz") val juzNumber: Int = 0,
    @ColumnInfo(name = "sora") val surahNumber: Int = 0,
    @ColumnInfo(name = "sora_name_en") val surahNameEn: String = "",
    @ColumnInfo(name = "sora_name_ar") val surahNameAr: String = "",
)