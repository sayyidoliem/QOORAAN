package com.olimhousestudio.qooraan.domain.usecase.bookmark

import com.olimhousestudio.qooraan.domain.model.bookmark.AyahBookmarkDomain
import com.olimhousestudio.qooraan.domain.repository.BookmarkRepository
import javax.inject.Inject

class ToggleAyahBookmarkUseCase @Inject constructor(
    private val repository: BookmarkRepository
) {
    suspend operator fun invoke(bookmark: AyahBookmarkDomain) {
        repository.toggleAyahBookmark(bookmark)
    }
}