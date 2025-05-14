package com.olimhousestudio.qooraan.data.model

import androidx.room.ColumnInfo
import androidx.room.DatabaseView
import androidx.room.PrimaryKey

@DatabaseView("SELECT id, sora_name_emlaey, aya_text_emlaey, COUNT (id) as ayah_total, jozz, sora_descend_place , sora from quran GROUP BY sora, aya_text_emlaey")
data class SurahSearch(
    @PrimaryKey val id: Int? = 0,
    @ColumnInfo(name = "sora") val surahNumber: Int? = 0,
    @ColumnInfo(name = "sora_name_emlaey") val surahNameEmlay: String? = "",
    @ColumnInfo(name = "aya_text_emlaey") val ayatNameEmlay: String? = "",
    @ColumnInfo(name = "jozz") val juzNumber: Int? = 0,
    @ColumnInfo(name = "ayah_total") val numberOfAyah: Int? = 0,
    @ColumnInfo(name = "sora_descend_place") val surahDescendPlace: String? = "",
)