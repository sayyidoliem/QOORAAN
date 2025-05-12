package com.olimhousestudio.qooraan.presentation.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkAdd
import androidx.compose.material.icons.filled.BookmarkAdded
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import com.olimhousestudio.qooraan.data.datasource.local.entities.SurahBookmark
import com.olimhousestudio.qooraan.data.datasource.local.BookmarkDatabase

import kotlinx.coroutines.launch


@Composable
fun SurahFavoriteButton(
    modifier: Modifier = Modifier,
    surahNumber: Int?,
    surahNameEn: String?,
    surahNameAr: String?,
    totalAyah: Int?,
    juzNumber: Int?,
    surahDescend: String?,
) {
    val context = LocalContext.current
    val dao = BookmarkDatabase.getInstance(context).bookmarkDao()
    val scope = rememberCoroutineScope()
    var isFavorite by remember { mutableStateOf(false) }

    LaunchedEffect(true) {
        isFavorite = dao.selectedFavoriteButton(surahNumber!!)
    }

    IconToggleButton(
        checked = isFavorite,
        onCheckedChange = {
            scope.launch {
                if (isFavorite) {
                    dao.deleteSurahBookmark(
                        SurahBookmark(
                            surahNameEn!!,
                            surahNameAr!!,
                            totalAyah!!,
                            juzNumber!!,
                            surahDescend,
                            surahNumber
                        )
                    )
                    isFavorite = false
                    return@launch
                }
                dao.insertSurahBookmark(
                    SurahBookmark(
                        surahNameEn!!,
                        surahNameAr!!,
                        totalAyah!!,
                        juzNumber!!,
                        surahDescend!!,
                        surahNumber
                    )
                )
                isFavorite = true
            }
        }
    ) {
        Icon(
            modifier = modifier.graphicsLayer {
                scaleX = 1.3f
                scaleY = 1.3f
            },
            imageVector = if (isFavorite) {
                Icons.Filled.BookmarkAdded
            } else {
                Icons.Default.BookmarkAdd
            },
            contentDescription = null
        )
    }
}