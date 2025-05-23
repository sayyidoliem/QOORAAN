package com.olimhousestudio.qooraan.data.model

import androidx.room.ColumnInfo
import androidx.room.DatabaseView
import androidx.room.PrimaryKey

@DatabaseView("SELECT id, sora, sora_name_ar, jozz ,sora_name_en, sora_descend_place, sora_name_id, COUNT(id) as ayah_total, jozz, sora_descend_place FROM quran GROUP by sora")
data class Surah(
    @PrimaryKey val id: Int? = 0,
    @ColumnInfo(name = "sora") val surahNumber: Int? = 0,
    @ColumnInfo(name = "sora_name_ar") val surahNameAr: String? = "",
    @ColumnInfo(name = "jozz") val juzNumber: Int? = 0,
    @ColumnInfo(name = "sora_name_en") val surahNameEn: String? = "",
    @ColumnInfo(name = "ayah_total") val numberOfAyah: Int? = 0,
    @ColumnInfo(name = "sora_descend_place") val surahDescendPlace: String? = "",
    @ColumnInfo(name = "sora_name_id") val surahNameID: String? = ""
)