package com.olimhousestudio.qooraan.data.datasource.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.olimhousestudio.qooraan.data.datasource.local.entities.SurahBookmark
import com.olimhousestudio.qooraan.data.datasource.local.entities.Bookmark

import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarkDao {
    @Query("SELECT * FROM bookmark")
    fun getAllBookmarks():Flow<List<Bookmark>>

    @Insert
    suspend fun insertBookmark(bookmark: Bookmark)

    @Delete
    suspend fun deleteBookmark(bookmark: Bookmark)

    @Query("DELETE FROM bookmark")
    suspend fun deleteAllFromBookmark()

    @Query("SELECT EXISTS(SELECT * FROM bookmark WHERE ayahNumber = :ayahNumber)")
    suspend fun selectedAyatFavoriteButton(ayahNumber: Int) : Boolean

    @Query("SELECT * FROM surahBookmark")
    fun getSurahBookmark():Flow<List<SurahBookmark>>

    @Insert
    suspend fun insertSurahBookmark(surahBookmark: SurahBookmark)

    @Delete
    suspend fun deleteSurahBookmark(surahBookmark: SurahBookmark)

    @Query("DELETE FROM surahBookmark")
    suspend fun deleteAllFromSurahBookmark()

    @Query("SELECT EXISTS(SELECT * FROM surahBookmark WHERE surahNumber = :surahNumber)")
    suspend fun selectedFavoriteButton(surahNumber: Int) : Boolean

}