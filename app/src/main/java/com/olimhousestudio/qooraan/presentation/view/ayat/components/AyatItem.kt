package com.olimhousestudio.qooraan.presentation.view.ayat.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.olimhousestudio.qooraan.domain.model.quran.AyatDomain
import com.olimhousestudio.qooraan.domain.model.AppSettings
import com.olimhousestudio.qooraan.presentation.component.SpannableText
import com.olimhousestudio.qooraan.utils.Converters.toAnnotatedString
import com.olimhousestudio.qooraan.utils.TajweedHelper

@Composable
fun AyatItem(
    ayat: AyatDomain,
    ayahTextSize: Float,
    selectedLanguage: Int,
    isVisibleTranslate: Boolean,
    isBookmarked: Boolean,
    onBookmarkClick: () -> Unit,
    onFootnoteClick: (String) -> Unit,
    onShareClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val lineHeight = ayahTextSize.sp * 1.3f

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            Card(
                modifier = Modifier
                    .padding(12.dp)
                    .size(34.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                ),
                shape = CircleShape
            ) {
                Box(Modifier.fillMaxSize()) {
                    Text(
                        text = "${ayat.ayatNumber ?: "-"}",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }

            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = TajweedHelper.getTajweed(
                        context = context,
                        s = Regex("\\d+\$").replace(
                            ayat.ayatText.orEmpty(),
                            ""
                        )
                    ).toAnnotatedString(MaterialTheme.colorScheme.primary),
                    fontSize = ayahTextSize.sp,
                    textAlign = TextAlign.End,
                    letterSpacing = 3.sp,
                    lineHeight = lineHeight
                )

                Spacer(modifier = Modifier.padding(5.dp))

                if (!isVisibleTranslate) {
                    SpannableText(
                        modifier = Modifier.align(Alignment.Start),
                        text = if (selectedLanguage == AppSettings.ENGLISH) {
                            ayat.footnotesEn.orEmpty()
                        } else {
                            ayat.footnotesId.orEmpty()
                        },
                        onClick = {
                            val footnote = if (selectedLanguage == AppSettings.ENGLISH) {
                                ayat.footnotesEn.orEmpty()
                            } else {
                                ayat.footnotesId.orEmpty()
                            }

                            onFootnoteClick(footnote)
                        }
                    )

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                    ) {
                        Row(
                            modifier = Modifier.align(Alignment.End)
                        ) {
                            IconButton(
                                onClick = onBookmarkClick
                            ) {
                                Icon(
                                    imageVector = if (isBookmarked) {
                                        Icons.Default.Bookmark
                                    } else {
                                        Icons.Default.BookmarkBorder
                                    },
                                    contentDescription = null
                                )
                            }

                            IconButton(
                                onClick = {
                                    onShareClick(ayat.ayatText.orEmpty())
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Share,
                                    contentDescription = null
                                )
                            }

                            IconButton(
                                onClick = {
                                    val footnote = if (selectedLanguage == AppSettings.ENGLISH) {
                                        ayat.footnotesEn.orEmpty()
                                    } else {
                                        ayat.footnotesId.orEmpty()
                                    }

                                    onFootnoteClick(footnote)
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Info,
                                    contentDescription = null
                                )
                            }
                        }
                    }
                }
            }
        }

        HorizontalDivider(
            modifier = Modifier.fillMaxWidth()
        )
    }
}