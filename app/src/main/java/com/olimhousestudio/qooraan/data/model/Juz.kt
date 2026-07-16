package com.olimhousestudio.qooraan.data.model

import androidx.room.ColumnInfo
import androidx.room.DatabaseView

@DatabaseView(
    """
    SELECT 
        jozz,
        sora,
        sora_name_en,
        sora_name_ar,
        MIN(aya_no) AS aya_no
    FROM quran
    GROUP BY jozz, sora, sora_name_en, sora_name_ar
    ORDER BY MIN(id) ASC
    """
)
data class Juz(
    @ColumnInfo(name = "jozz")
    val juzNumber: Int = 0,

    @ColumnInfo(name = "sora")
    val surahNumber: Int = 0,

    @ColumnInfo(name = "sora_name_en")
    val surahNameEn: String = "",

    @ColumnInfo(name = "sora_name_ar")
    val surahNameAr: String = "",

    @ColumnInfo(name = "aya_no")
    val ayahNumber: Int = 0,
)
