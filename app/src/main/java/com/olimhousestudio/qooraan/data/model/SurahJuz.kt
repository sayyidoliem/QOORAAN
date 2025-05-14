package com.olimhousestudio.qooraan.data.model

import androidx.room.ColumnInfo
import androidx.room.DatabaseView
import androidx.room.PrimaryKey

@DatabaseView("SELECT DISTINCT sora_name_en, sora_name_ar FROM quran  GROUP by sora ")
data class SurahJuz(
    @PrimaryKey val id: Int? = 0,
    @ColumnInfo(name = "sora") val surahNumber: Int? = 0,
    @ColumnInfo(name = "sora_name_ar") val surahNameAr: String? = "",
    @ColumnInfo(name = "jozz") val juzNumber: Int? = 0,
    @ColumnInfo(name = "sora_name_en") val surahNameEN: String? = "",
    @ColumnInfo(name = "ayah_total") val numberOfAyah: Int? = 0,
    @ColumnInfo(name = "sora_descend_place") val surahDescendPlace: String? = "",
    @ColumnInfo(name = "sora_name_id") val surahNameID: String? = ""
)