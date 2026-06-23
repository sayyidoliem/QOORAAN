package com.olimhousestudio.qooraan.domain.usecase.bookmark

import com.olimhousestudio.qooraan.domain.model.bookmark.SurahBookmarkDomain
import com.olimhousestudio.qooraan.domain.repository.BookmarkRepository
import javax.inject.Inject

class AddSurahBookmarkUseCase @Inject constructor(
    private val repository: BookmarkRepository
) {
    suspend operator fun invoke(bookmark: SurahBookmarkDomain) {
        repository.addSurahBookmark(bookmark)
    }
}