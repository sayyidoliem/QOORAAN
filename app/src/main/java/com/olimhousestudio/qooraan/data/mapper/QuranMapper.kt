package com.olimhousestudio.qooraan.data.mapper

import com.olimhousestudio.qooraan.data.datasource.local.entities.Qoran
import com.olimhousestudio.qooraan.data.model.AyatSearch
import com.olimhousestudio.qooraan.data.model.Juz
import com.olimhousestudio.qooraan.data.model.Page
import com.olimhousestudio.qooraan.data.model.Surah
import com.olimhousestudio.qooraan.data.model.SurahSearch
import com.olimhousestudio.qooraan.domain.model.quran.AyatDomain
import com.olimhousestudio.qooraan.domain.model.quran.AyatSearchDomain
import com.olimhousestudio.qooraan.domain.model.quran.JuzDomain
import com.olimhousestudio.qooraan.domain.model.quran.PageDomain
import com.olimhousestudio.qooraan.domain.model.quran.SurahDomain
import com.olimhousestudio.qooraan.domain.model.quran.SurahSearchDomain

fun Qoran.toDomain(): AyatDomain {
    return AyatDomain(
        id = id,
        juzNumber = juzNumber ?: 0,
        surahNumber = surahNumber ?: 0,
        surahNameEn = surahNameEn.orEmpty(),
        surahNameAr = surahNameAr.orEmpty(),
        page = page ?: 0,
        ayatNumber = ayatNumber ?: 0,
        ayatText = ayatText.orEmpty(),
        ayatTextEmlaey = ayatTextEmlaey.orEmpty(),
        translateId = tranlateId.orEmpty(),
        footnotesId = footnotesId.orEmpty(),
        surahNameId = surahNameId.orEmpty(),
        surahDescendPlace = surahDescendPlace.orEmpty(),
        surahNameEmlaey = surahNameEmlaey.orEmpty(),
        translateEn = tranlateEn.orEmpty(),
        footnotesEn = footnotesEn.orEmpty()
    )
}

fun Surah.toDomain(): SurahDomain {
    return SurahDomain(
        id = id ?: 0,
        surahNumber = surahNumber,
        surahNameAr = surahNameAr.orEmpty(),
        juzNumber = juzNumber ?: 0,
        surahNameEn = surahNameEn.orEmpty(),
        numberOfAyah = numberOfAyah ?: 0,
        surahDescendPlace = surahDescendPlace.orEmpty(),
        surahNameId = surahNameID.orEmpty()
    )
}

fun Juz.toDomain(): JuzDomain {
    return JuzDomain(
        juzNumber = juzNumber,
        surahNumber = surahNumber,
        surahNameEn = surahNameEn,
        surahNameAr = surahNameAr,
        ayahNumber = ayahNumber
    )
}

fun Page.toDomain(): PageDomain {
    return PageDomain(
        page = page,
        surahNameEn = surahNameEn,
        surahNameAr = surahNameAr.orEmpty(),
        surahNumber = surahNumber
    )
}

fun SurahSearch.toDomain(): SurahSearchDomain {
    return SurahSearchDomain(
        id = id ?: 0,
        surahNumber = surahNumber,
        surahNameEmlay = surahNameEmlay.orEmpty(),
        ayatNameEmlay = ayatNameEmlay.orEmpty(),
        juzNumber = juzNumber ?: 0,
        numberOfAyah = numberOfAyah ?: 0,
        surahDescendPlace = surahDescendPlace.orEmpty()
    )
}

fun AyatSearch.toDomain(): AyatSearchDomain {
    return AyatSearchDomain(
        id = id ?: 0,
        surahNumber = surahNumber ?: 0,
        ayatTextEmlay = ayatTextEmlay.orEmpty(),
        surahNameEmlaey = surahNameEmlaey.orEmpty(),
        ayatText = ayatText.orEmpty(),
        translateEn = tranlateEn.orEmpty(),
        translateId = tranlateId.orEmpty(),
        ayatNumber = ayatNumber ?: 0,
        numberOfAyah = numberOfAyah ?: 0,
        surahDescendPlace = surahDescendPlace.orEmpty()
    )
}