package com.olimhousestudio.qooraan.data.model

import androidx.room.ColumnInfo
import androidx.room.DatabaseView
import androidx.room.PrimaryKey

@DatabaseView(
    """
    SELECT 
        MIN(id) AS id,
        sora,
        sora_name_ar,
        MIN(jozz) AS jozz,
        sora_name_en,
        COUNT(id) AS ayah_total,
        sora_descend_place,
        sora_name_id
    FROM quran
    GROUP BY 
        sora,
        sora_name_ar,
        sora_name_en,
        sora_descend_place,
        sora_name_id
    ORDER BY sora ASC
    """
)
data class SurahJuz(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "sora")
    val surahNumber: Int = 0,

    @ColumnInfo(name = "sora_name_ar")
    val surahNameAr: String = "",

    @ColumnInfo(name = "jozz")
    val juzNumber: Int = 0,

    @ColumnInfo(name = "sora_name_en")
    val surahNameEN: String = "",

    @ColumnInfo(name = "ayah_total")
    val numberOfAyah: Int = 0,

    @ColumnInfo(name = "sora_descend_place")
    val surahDescendPlace: String = "",

    @ColumnInfo(name = "sora_name_id")
    val surahNameID: String = ""
)