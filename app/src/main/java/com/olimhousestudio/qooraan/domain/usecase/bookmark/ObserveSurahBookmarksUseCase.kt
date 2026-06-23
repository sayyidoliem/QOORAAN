package com.olimhousestudio.qooraan.domain.usecase.bookmark

import com.olimhousestudio.qooraan.domain.repository.BookmarkRepository
import javax.inject.Inject

class ObserveSurahBookmarksUseCase @Inject constructor(
    private val repository: BookmarkRepository
) {
    operator fun invoke() = repository.getSurahBookmarks()
}