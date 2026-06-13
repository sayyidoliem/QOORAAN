package com.olimhousestudio.qooraan.domain.repository

import com.olimhousestudio.qooraan.domain.model.bookmark.AyahBookmarkDomain
import com.olimhousestudio.qooraan.domain.model.bookmark.SurahBookmarkDomain
import kotlinx.coroutines.flow.Flow

interface BookmarkRepository {

    fun getAyahBookmarks(): Flow<List<AyahBookmarkDomain>>

    fun getSurahBookmarks(): Flow<List<SurahBookmarkDomain>>

    suspend fun addAyahBookmark(bookmark: AyahBookmarkDomain)

    suspend fun removeAyahBookmark(bookmark: AyahBookmarkDomain)

    suspend fun addSurahBookmark(bookmark: SurahBookmarkDomain)

    suspend fun removeSurahBookmark(bookmark: SurahBookmarkDomain)

    suspend fun clearAyahBookmarks()

    suspend fun clearSurahBookmarks()

    suspend fun isSurahBookmarked(surahNumber: Int): Boolean

    suspend fun isAyahBookmarked(
        surahNumber: Int,
        ayahNumber: Int
    ): Boolean

    suspend fun toggleSurahBookmark(bookmark: SurahBookmarkDomain)

    suspend fun toggleAyahBookmark(bookmark: AyahBookmarkDomain)

    suspend fun removeAyahBookmarkByNumber(
        surahNumber: Int,
        ayahNumber: Int
    )
}