package com.olimhousestudio.qooraan.data.datasource.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.olimhousestudio.qooraan.data.datasource.local.entities.Bookmark
import com.olimhousestudio.qooraan.data.datasource.local.entities.SurahBookmark
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarkDao {

    @Query("SELECT * FROM bookmark ORDER BY createAt DESC")
    fun getAllBookmarks(): Flow<List<Bookmark>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookmark(bookmark: Bookmark)

    @Delete
    suspend fun deleteBookmark(bookmark: Bookmark)

    @Query("DELETE FROM bookmark")
    suspend fun deleteAllFromBookmark()

    @Query(
        """
        SELECT EXISTS(
            SELECT * FROM bookmark 
            WHERE surahNumber = :surahNumber 
            AND ayahNumber = :ayahNumber
        )
        """
    )
    suspend fun isAyahBookmarked(
        surahNumber: Int,
        ayahNumber: Int
    ): Boolean

    @Query("SELECT * FROM surahBookmark ORDER BY surahNumber ASC")
    fun getSurahBookmark(): Flow<List<SurahBookmark>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSurahBookmark(surahBookmark: SurahBookmark)

    @Delete
    suspend fun deleteSurahBookmark(surahBookmark: SurahBookmark)

    @Query("DELETE FROM surahBookmark")
    suspend fun deleteAllFromSurahBookmark()

    @Query("SELECT EXISTS(SELECT * FROM surahBookmark WHERE surahNumber = :surahNumber)")
    suspend fun selectedFavoriteButton(surahNumber: Int): Boolean

    @Query(
        """
    DELETE FROM bookmark 
    WHERE surahNumber = :surahNumber 
    AND ayahNumber = :ayahNumber
    """
    )
    suspend fun deleteBookmarkByAyah(
        surahNumber: Int,
        ayahNumber: Int
    )
}