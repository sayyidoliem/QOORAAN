package com.olimhousestudio.qooraan.presentation.view.ayat

import android.content.Intent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.olimhousestudio.qooraan.presentation.view.ayat.components.AyatHeaderCard
import com.olimhousestudio.qooraan.presentation.view.ayat.components.AyatItem
import com.olimhousestudio.qooraan.presentation.view.ayat.components.FootnoteBottomSheet
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AyatScreen(
    uiState: AyatUiState,
    initialScrollIndex: Int,
    goBack: () -> Unit,
    onEvent: (AyatEvent) -> Unit
) {
    val context = LocalContext.current
    val listState = rememberLazyListState()

    LaunchedEffect(
        initialScrollIndex,
        uiState.ayatList.size
    ) {
        if (initialScrollIndex >= 0 && uiState.ayatList.isNotEmpty()) {
            delay(300)
            listState.animateScrollToItem(initialScrollIndex)
        }
    }

    Scaffold(
        topBar = {
            if (uiState.isSearchActive) {
                DockedSearchBar(
                    query = uiState.searchQuery,
                    onQueryChange = {
                        onEvent(AyatEvent.SearchQueryChanged(it))
                    },
                    onSearch = {
                        onEvent(AyatEvent.Search)
                    },
                    active = uiState.isSearchActive,
                    onActiveChange = {
                        onEvent(AyatEvent.SearchActiveChanged(it))
                    },
                    placeholder = {
                        Text(text = "Search")
                    },
                    leadingIcon = {
                        IconButton(
                            onClick = {
                                onEvent(AyatEvent.SearchActiveChanged(false))
                            }
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = null
                            )
                        }
                    },
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                onEvent(AyatEvent.ClearSearch)
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = null
                            )
                        }
                    },
                    shape = SearchBarDefaults.dockedShape
                ) {
                    LazyColumn {
                        itemsIndexed(uiState.searchResults) { _, item ->
                            ListItem(
                                headlineContent = {
                                    Text(text = item.ayatText.orEmpty())
                                },
                                supportingContent = {
                                    Text(text = item.translateId.orEmpty())
                                }
                            )
                        }
                    }
                }
            } else {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = uiState.surahNameEn.ifBlank {
                                "Read"
                            }
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = goBack) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = null
                            )
                        }
                    },
                    actions = {
                        IconButton(
                            onClick = {
                                onEvent(AyatEvent.SearchActiveChanged(true))
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = null
                            )
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = listState,
            contentPadding = innerPadding
        ) {
            item {
                if (uiState.ayatList.isNotEmpty()) {
                    AyatHeaderCard(uiState = uiState)
                }
            }

            itemsIndexed(
                items = uiState.ayatList,
                key = { _, item -> item.id }
            ) { index, ayat ->
                val key = ayahBookmarkKey(
                    surahNumber = ayat.surahNumber,
                    ayahNumber = ayat.ayatNumber
                )

                val isBookmarked = uiState.bookmarkedAyahKeys.contains(key)

                AyatItem(
                    ayat = ayat,
                    ayahTextSize = uiState.ayahTextSize,
                    selectedLanguage = uiState.selectedLanguage,
                    isVisibleTranslate = uiState.isVisibleTranslate,
                    isBookmarked = isBookmarked,
                    onBookmarkClick = {
                        onEvent(AyatEvent.ToggleAyahBookmark(ayat))
                    },
                    onFootnoteClick = { footnote ->
                        onEvent(AyatEvent.ShowFootnote(footnote))
                    },
                    onShareClick = { text ->
                        val sendIntent = Intent().apply {
                            action = Intent.ACTION_SEND
                            putExtra(Intent.EXTRA_TEXT, text)
                            type = "text/plain"
                        }

                        context.startActivity(
                            Intent.createChooser(sendIntent, null)
                        )
                    }
                )

                LaunchedEffect(index) {
                    onEvent(
                        AyatEvent.UpdateLastRead(
                            ayat = ayat,
                            index = index
                        )
                    )
                }
            }
        }
    }

    if (uiState.isFootnoteSheetVisible) {
        FootnoteBottomSheet(
            footnote = uiState.selectedFootnote,
            onDismiss = {
                onEvent(AyatEvent.HideFootnote)
            },
            onShare = { text ->
                val sendIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, text)
                    type = "text/plain"
                }

                context.startActivity(
                    Intent.createChooser(sendIntent, null)
                )
            }
        )
    }
}