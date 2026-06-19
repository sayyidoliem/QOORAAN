package com.olimhousestudio.qooraan.domain.model

import com.olimhousestudio.qooraan.common.Qories

data class AppSettings(
    val selectedLanguage: Int = INDONESIA,
    val isDarkMode: Boolean = false,
    val isVisibleTranslate: Boolean = false,
    val selectedQori: Qories = Qories.ABDUL_SOMAD,
    val isOnBoarding: Boolean = true,
    val ayahTextSize: Float = 32f
) {
    companion object {
        const val INDONESIA = 0
        const val ENGLISH = 1
    }
}