package com.olimhousestudio.qooraan.data.repositories

import com.olimhousestudio.qooraan.data.datasource.local.BookmarkDao
import com.olimhousestudio.qooraan.data.mapper.toDomain
import com.olimhousestudio.qooraan.data.mapper.toEntity
import com.olimhousestudio.qooraan.domain.model.bookmark.AyahBookmarkDomain
import com.olimhousestudio.qooraan.domain.model.bookmark.SurahBookmarkDomain
import com.olimhousestudio.qooraan.domain.repository.BookmarkRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BookmarkRepositoryImpl @Inject constructor(
    private val bookmarkDao: BookmarkDao
) : BookmarkRepository {

    override fun getAyahBookmarks(): Flow<List<AyahBookmarkDomain>> {
        return bookmarkDao.getAllBookmarks().map { list ->
            list.map { it.toDomain() }
        }
    }

    override fun getSurahBookmarks(): Flow<List<SurahBookmarkDomain>> {
        return bookmarkDao.getSurahBookmark().map { list ->
            list.map { it.toDomain() }
        }
    }

    override suspend fun addAyahBookmark(bookmark: AyahBookmarkDomain) {
        bookmarkDao.insertBookmark(bookmark.toEntity())
    }

    override suspend fun removeAyahBookmark(bookmark: AyahBookmarkDomain) {
        bookmarkDao.deleteBookmark(bookmark.toEntity())
    }

    override suspend fun addSurahBookmark(bookmark: SurahBookmarkDomain) {
        bookmarkDao.insertSurahBookmark(bookmark.toEntity())
    }

    override suspend fun removeSurahBookmark(bookmark: SurahBookmarkDomain) {
        bookmarkDao.deleteSurahBookmark(bookmark.toEntity())
    }

    override suspend fun clearAyahBookmarks() {
        bookmarkDao.deleteAllFromBookmark()
    }

    override suspend fun clearSurahBookmarks() {
        bookmarkDao.deleteAllFromSurahBookmark()
    }

    override suspend fun isSurahBookmarked(surahNumber: Int): Boolean {
        return bookmarkDao.selectedFavoriteButton(surahNumber)
    }

    override suspend fun isAyahBookmarked(
        surahNumber: Int,
        ayahNumber: Int
    ): Boolean {
        return bookmarkDao.isAyahBookmarked(
            surahNumber = surahNumber,
            ayahNumber = ayahNumber
        )
    }

    override suspend fun toggleSurahBookmark(bookmark: SurahBookmarkDomain) {
        val isExist = bookmarkDao.selectedFavoriteButton(bookmark.surahNumber)

        if (isExist) {
            bookmarkDao.deleteSurahBookmark(bookmark.toEntity())
        } else {
            bookmarkDao.insertSurahBookmark(bookmark.toEntity())
        }
    }

    override suspend fun toggleAyahBookmark(bookmark: AyahBookmarkDomain) {
        val isExist = bookmarkDao.isAyahBookmarked(
            surahNumber = bookmark.surahNumber,
            ayahNumber = bookmark.ayahNumber
        )

        if (isExist) {
            bookmarkDao.deleteBookmarkByAyah(
                surahNumber = bookmark.surahNumber,
                ayahNumber = bookmark.ayahNumber
            )
        } else {
            bookmarkDao.insertBookmark(bookmark.toEntity())
        }
    }

    override suspend fun removeAyahBookmarkByNumber(
        surahNumber: Int,
        ayahNumber: Int
    ) {
        bookmarkDao.deleteBookmarkByAyah(
            surahNumber = surahNumber,
            ayahNumber = ayahNumber
        )
    }
}