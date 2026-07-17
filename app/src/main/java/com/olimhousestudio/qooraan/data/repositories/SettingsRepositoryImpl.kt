package com.olimhousestudio.qooraan.data.repositories

import com.olimhousestudio.qooraan.common.Qories
import com.olimhousestudio.qooraan.data.datasource.local.preference.AppPreferencesDataSource
import com.olimhousestudio.qooraan.domain.model.AppSettings
import com.olimhousestudio.qooraan.domain.repositories.SettingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
    private val preferencesDataSource: AppPreferencesDataSource
) : SettingsRepository {

    override val settingsFlow: Flow<AppSettings>
        get() = preferencesDataSource.settingsFlow

    override suspend fun setSelectedLanguage(language: Int) {
        preferencesDataSource.setSelectedLanguage(language)
    }

    override suspend fun setDarkMode(isDarkMode: Boolean) {
        preferencesDataSource.setDarkMode(isDarkMode)
    }

    override suspend fun setVisibleTranslate(isVisibleTranslate: Boolean) {
        preferencesDataSource.setVisibleTranslate(isVisibleTranslate)
    }

    override suspend fun setSelectedQori(qori: Qories) {
        preferencesDataSource.setSelectedQori(qori)
    }

    override suspend fun setOnBoarding(isOnBoarding: Boolean) {
        preferencesDataSource.setOnBoarding(isOnBoarding)
    }

    override suspend fun setAyahTextSize(size: Float) {
        preferencesDataSource.setAyahTextSize(size)
    }
}
