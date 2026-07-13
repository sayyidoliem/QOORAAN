package com.olimhousestudio.qooraan.data.mapper

import com.olimhousestudio.qooraan.data.datasource.local.entities.Bookmark
import com.olimhousestudio.qooraan.data.datasource.local.entities.SurahBookmark
import com.olimhousestudio.qooraan.domain.model.bookmark.AyahBookmarkDomain
import com.olimhousestudio.qooraan.domain.model.bookmark.SurahBookmarkDomain

fun Bookmark.toDomain(): AyahBookmarkDomain {
    return AyahBookmarkDomain(
        id = id,
        surahName = surahName.orEmpty(),
        ayahNumber = ayahNumber,
        surahNumber = surahNumber,
        createdAt = createAt
    )
}

fun AyahBookmarkDomain.toEntity(): Bookmark {
    return Bookmark(
        id = id,
        surahName = surahName,
        ayahNumber = ayahNumber,
        surahNumber = surahNumber,
        createAt = createdAt
    )
}

fun SurahBookmark.toDomain(): SurahBookmarkDomain {
    return SurahBookmarkDomain(
        surahNameEn = surahNameEn.orEmpty(),
        surahNameAr = surahNameAr.orEmpty(),
        totalAyah = totalAyah ?: 0,
        juzNumber = juzNumber ?: 0,
        surahDescend = surahDescend.orEmpty(),
        surahNumber = surahNumber ?: 0
    )
}

fun SurahBookmarkDomain.toEntity(): SurahBookmark {
    return SurahBookmark(
        surahNameEn = surahNameEn,
        surahNameAr = surahNameAr,
        totalAyah = totalAyah,
        juzNumber = juzNumber,
        surahDescend = surahDescend,
        surahNumber = surahNumber
    )
}