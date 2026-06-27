package com.olimhousestudio.qooraan.domain.usecase.settings

import com.olimhousestudio.qooraan.domain.repositories.SettingsRepository
import javax.inject.Inject

class SetSelectedLanguageUseCase @Inject constructor(
    private val repository: SettingsRepository
) {
    suspend operator fun invoke(language: Int) {
        repository.setSelectedLanguage(language)
    }
}