package com.olimhousestudio.qooraan.presentation.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SurahFavoriteButton(
    modifier: Modifier = Modifier,
    isBookmarked: Boolean,
    onToggleBookmark: () -> Unit
) {
    IconButton(
        modifier = modifier,
        onClick = onToggleBookmark
    ) {
        Icon(
            imageVector = if (isBookmarked) {
                Icons.Filled.Bookmark
            } else {
                Icons.Outlined.BookmarkBorder
            },
            contentDescription = "Favorite"
        )
    }
}