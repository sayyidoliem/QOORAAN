package com.olimhousestudio.qooraan.data.model

import androidx.room.ColumnInfo
import androidx.room.DatabaseView
import androidx.room.PrimaryKey

@DatabaseView("SELECT id, aya_text_emlaey, sora_name_emlaey, sora, COUNT (id) as ayah_total, aya_no, aya_text, sora_descend_place, translation_en, translation_id sora from quran GROUP BY sora")
data class AyatSearch(
    @PrimaryKey val id: Int? = 0,
    @ColumnInfo(name = "sora") val surahNumber:Int? = 0,
    @ColumnInfo(name = "aya_text_emlaey") val ayatTextEmlay: String? = "",
    @ColumnInfo(name = "sora_name_emlaey") val surahNameEmlaey : String? = "" ,
    @ColumnInfo(name = "aya_text") val ayatText : String? = "",
    @ColumnInfo(name = "translation_en") val tranlateEn: String? = "",
    @ColumnInfo(name = "translation_id") val tranlateId : String? = "",
    @ColumnInfo(name = "aya_no") var ayatNumber : Int? = 0,
    @ColumnInfo(name = "ayah_total") val numberOfAyah: Int? = 0,
    @ColumnInfo(name = "sora_descend_place") val surahDescendPlace  :String? = "",
)