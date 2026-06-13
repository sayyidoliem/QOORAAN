package com.olimhousestudio.qooraan.domain.repositories

import com.olimhousestudio.qooraan.common.Qories
import com.olimhousestudio.qooraan.domain.model.AppSettings
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {

    val settingsFlow: Flow<AppSettings>

    suspend fun setSelectedLanguage(language: Int)

    suspend fun setDarkMode(isDarkMode: Boolean)

    suspend fun setVisibleTranslate(isVisibleTranslate: Boolean)

    suspend fun setSelectedQori(qori: Qories)

    suspend fun setOnBoarding(isOnBoarding: Boolean)

    suspend fun setAyahTextSize(size: Float)
}