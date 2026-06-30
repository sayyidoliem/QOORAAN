package com.olimhousestudio.qooraan.data.datasource.local.preference

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.olimhousestudio.qooraan.common.Qories
import com.olimhousestudio.qooraan.domain.model.AppSettings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.appDataStore by preferencesDataStore(
    name = "qooraan_app_preferences"
)

class AppPreferencesDataSource(
    private val context: Context
) {

    val settingsFlow: Flow<AppSettings> =
        context.appDataStore.data.map { preferences ->
            val selectedQoriId = preferences[SELECTED_QORI_ID]
                ?: Qories.ABDUL_SOMAD.id

            AppSettings(
                selectedLanguage = preferences[SELECTED_LANGUAGE]
                    ?: AppSettings.INDONESIA,
                isDarkMode = preferences[IS_DARK_MODE]
                    ?: false,
                isVisibleTranslate = preferences[IS_VISIBLE_TRANSLATE]
                    ?: false,
                selectedQori = Qories.values().firstOrNull { it.id == selectedQoriId }
                    ?: Qories.ABDUL_SOMAD,
                isOnBoarding = preferences[IS_ONBOARDING]
                    ?: true,
                ayahTextSize = preferences[AYAH_TEXT_SIZE]
                    ?: 32f
            )
        }

    suspend fun setSelectedLanguage(language: Int) {
        context.appDataStore.edit { preferences ->
            preferences[SELECTED_LANGUAGE] = language
        }
    }

    suspend fun setDarkMode(isDarkMode: Boolean) {
        context.appDataStore.edit { preferences ->
            preferences[IS_DARK_MODE] = isDarkMode
        }
    }

    suspend fun setVisibleTranslate(isVisibleTranslate: Boolean) {
        context.appDataStore.edit { preferences ->
            preferences[IS_VISIBLE_TRANSLATE] = isVisibleTranslate
        }
    }

    suspend fun setSelectedQori(qori: Qories) {
        context.appDataStore.edit { preferences ->
            preferences[SELECTED_QORI_ID] = qori.id
        }
    }

    suspend fun setOnBoarding(isOnBoarding: Boolean) {
        context.appDataStore.edit { preferences ->
            preferences[IS_ONBOARDING] = isOnBoarding
        }
    }

    suspend fun setAyahTextSize(size: Float) {
        context.appDataStore.edit { preferences ->
            preferences[AYAH_TEXT_SIZE] = size
        }
    }

    private companion object {
        val SELECTED_LANGUAGE = intPreferencesKey("selected_language")
        val IS_DARK_MODE = booleanPreferencesKey("is_dark_mode")
        val IS_VISIBLE_TRANSLATE = booleanPreferencesKey("is_visible_translate")
        val SELECTED_QORI_ID = stringPreferencesKey("selected_qori_id")
        val IS_ONBOARDING = booleanPreferencesKey("is_onboarding")
        val AYAH_TEXT_SIZE = floatPreferencesKey("ayah_text_size")
    }
}