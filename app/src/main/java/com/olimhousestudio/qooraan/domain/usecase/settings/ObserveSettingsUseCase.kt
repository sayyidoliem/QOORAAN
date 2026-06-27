package com.olimhousestudio.qooraan.domain.usecase.settings

import com.olimhousestudio.qooraan.domain.repositories.SettingsRepository
import javax.inject.Inject

class ObserveSettingsUseCase @Inject constructor(
    private val repository: SettingsRepository
) {
    operator fun invoke() = repository.settingsFlow
}