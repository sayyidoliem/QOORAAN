package com.olimhousestudio.qooraan.data.datasource.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmark")
data class Bookmark(
    val surahName: String? = "",
    val ayahNumber: Int,
    val surahNumber: Int,
    @PrimaryKey(autoGenerate = true)val id: Int? = null,
    val createAt : Long = System.currentTimeMillis(),
)
