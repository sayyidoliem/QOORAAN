package com.olimhousestudio.qooraan.domain.usecase.settings

import com.olimhousestudio.qooraan.common.Qories
import com.olimhousestudio.qooraan.domain.repositories.SettingsRepository
import javax.inject.Inject

class SetSelectedQoriUseCase @Inject constructor(
    private val repository: SettingsRepository
) {
    suspend operator fun invoke(qori: Qories) {
        repository.setSelectedQori(qori)
    }
}