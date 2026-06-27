package com.olimhousestudio.qooraan.domain.usecase.settings

import com.olimhousestudio.qooraan.domain.repositories.SettingsRepository
import javax.inject.Inject

class SetVisibleTranslateUseCase @Inject constructor(
    private val repository: SettingsRepository
) {
    suspend operator fun invoke(isVisibleTranslate: Boolean) {
        repository.setVisibleTranslate(isVisibleTranslate)
    }
}