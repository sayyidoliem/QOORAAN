package com.olimhousestudio.qooraan.utils

import com.chibatching.kotpref.KotprefModel
import com.chibatching.kotpref.enumpref.enumOrdinalPref
import com.olimhousestudio.qooraan.common.Qories

object SettingPreferences : KotprefModel() {
    //for change language
    const val INDONESIA = 0
    const val ENGLISH = 1

    private const val LIGHT_MODE = false

    const val FOCUS_READ = false

    var isSelectedLanguage by intPref(
        INDONESIA
    )

    var isDarkMode by booleanPref(
        LIGHT_MODE
    )

    var isVisibleTranslate by booleanPref(
        FOCUS_READ
    )

    var dateFormat by longPref(-1)

    var selectedQori by enumOrdinalPref(
        Qories.ABDUL_SOMAD
    )

    var isOnBoarding by booleanPref(true)

    private const val SLIDER_DEF_VALUE = 32F

    var ayahTextSize by floatPref(SLIDER_DEF_VALUE)

}
