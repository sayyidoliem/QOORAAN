package com.olimhousestudio.qooraan.data.datasource.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "surahBookmark")
data class SurahBookmark(
    val surahNameEn : String? = "",
    val surahNameAr : String? = "",
    val totalAyah : Int? = 0,
    val juzNumber : Int? = 0,
    val surahDescend : String? = "",
    @PrimaryKey val surahNumber : Int? = 0,
)
