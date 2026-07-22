package com.olimhousestudio.qooraan.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Sort
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.olimhousestudio.qooraan.domain.model.bookmark.AyahBookmarkDomain
import com.olimhousestudio.qooraan.domain.model.bookmark.SurahBookmarkDomain
import com.olimhousestudio.qooraan.presentation.viewmodel.bookmark.BookmarkType
import com.olimhousestudio.qooraan.presentation.viewmodel.bookmark.BookmarkUiState
import com.olimhousestudio.qooraan.presentation.viewmodel.bookmark.BookmarkViewModel
import com.olimhousestudio.qooraan.domain.model.AppSettings
import java.text.SimpleDateFormat
import java.util.Date
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.platform.LocalLocale
import com.olimhousestudio.qooraan.domain.model.LastRead
import java.util.Locale

@Composable
fun BookmarkScreens(
    modifier: Modifier,
    goToRead: (surahNumber: Int?, juzNumber: Int?, pageNumber: Int?, index: Int?) -> Unit
) {
    val viewModel: BookmarkViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()

    BookmarkScreenContent(
        modifier = modifier,
        uiState = uiState,
        onTypeChange = viewModel::changeBookmarkType,
        onDeleteSurahBookmark = viewModel::deleteSurahBookmark,
        onUndoDeleteSurahBookmark = viewModel::undoDeleteSurahBookmark,
        onDeleteAyahBookmark = viewModel::deleteAyahBookmark,
        onUndoDeleteAyahBookmark = viewModel::undoDeleteAyahBookmark,
        goToRead = goToRead
    )
}

@Composable
private fun BookmarkScreenContent(
    modifier: Modifier,
    uiState: BookmarkUiState,
    onTypeChange: (BookmarkType) -> Unit,
    onDeleteSurahBookmark: (SurahBookmarkDomain) -> Unit,
    onUndoDeleteSurahBookmark: () -> Unit,
    onDeleteAyahBookmark: (AyahBookmarkDomain) -> Unit,
    onUndoDeleteAyahBookmark: () -> Unit,
    goToRead: (surahNumber: Int?, juzNumber: Int?, pageNumber: Int?, index: Int?) -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }

    val isIndonesia = uiState.selectedLanguage == AppSettings.INDONESIA

    var isTypeDialogOpen by remember { mutableStateOf(false) }
    var tempSelectedType by remember(uiState.selectedType) {
        mutableStateOf(uiState.selectedType)
    }

    val removeMessage = if (isIndonesia) {
        "Hapus bookmark"
    } else {
        "Remove bookmark"
    }

    val undoMessage = if (isIndonesia) {
        "Kembalikan"
    } else {
        "Undo"
    }

    Scaffold(
        modifier = modifier,
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize()
        ) {
            when (uiState.selectedType) {
                BookmarkType.SURAH -> {
                    SurahBookmarkList(
                        bookmarks = uiState.surahBookmarks,
                        lastRead = uiState.lastRead,
                        selectedLanguage = uiState.selectedLanguage,
                        onOpenTypeDialog = { isTypeDialogOpen = true },
                        onDelete = onDeleteSurahBookmark,
                        goToRead = goToRead
                    )
                }

                BookmarkType.AYAH -> {
                    AyahBookmarkList(
                        bookmarks = uiState.ayahBookmarks,
                        lastRead = uiState.lastRead,
                        selectedLanguage = uiState.selectedLanguage,
                        goToRead = goToRead,
                        onOpenTypeDialog = { isTypeDialogOpen = true },
                        onDelete = onDeleteAyahBookmark
                    )
                }
            }
        }
    }

    LaunchedEffect(uiState.recentlyDeletedSurahBookmark) {
        if (uiState.recentlyDeletedSurahBookmark != null) {
            val result = snackbarHostState.showSnackbar(
                message = removeMessage,
                actionLabel = undoMessage,
                duration = SnackbarDuration.Short
            )

            if (result == SnackbarResult.ActionPerformed) {
                onUndoDeleteSurahBookmark()
            }
        }
    }

    LaunchedEffect(uiState.recentlyDeletedAyahBookmark) {
        if (uiState.recentlyDeletedAyahBookmark != null) {
            val result = snackbarHostState.showSnackbar(
                message = removeMessage,
                actionLabel = undoMessage,
                duration = SnackbarDuration.Short
            )

            if (result == SnackbarResult.ActionPerformed) {
                onUndoDeleteAyahBookmark()
            }
        }
    }

    if (isTypeDialogOpen) {
        BookmarkTypeDialog(
            selectedType = tempSelectedType,
            onSelectedTypeChange = { tempSelectedType = it },
            onConfirm = {
                onTypeChange(tempSelectedType)
                isTypeDialogOpen = false
            },
            onDismiss = {
                tempSelectedType = uiState.selectedType
                isTypeDialogOpen = false
            }
        )
    }
}

@Composable
private fun LastReadSection(
    lastRead: LastRead,
    selectedLanguage: Int,
    goToRead: (surahNumber: Int?, juzNumber: Int?, pageNumber: Int?, index: Int?) -> Unit
) {
    val isIndonesia = selectedLanguage == AppSettings.INDONESIA

    val lastDateText = if (lastRead.lastDate > 0) {
        SimpleDateFormat(
            "dd/MMM/yyyy",
            LocalLocale.current.platformLocale
        ).format(Date(lastRead.lastDate))
    } else {
        "-"
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = if (isIndonesia) {
                "Terakhir Dibaca"
            } else {
                "Last Read"
            },
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }

    if (lastRead.surahName.isNotEmpty()) {
        ListItem(
            headlineContent = {
                Text(
                    text = lastRead.surahName,
                    style = MaterialTheme.typography.titleLarge
                )
            },
            leadingContent = {
                NumberCard(number = lastRead.surahNumber.toString())
            },
            trailingContent = {
                Text(text = lastDateText)
            },
            supportingContent = {
                Text(text = "Ayat ${lastRead.ayahNumber}")
            },
            modifier = Modifier.clickable {
                goToRead(
                    lastRead.surahNumber,
                    null,
                    null,
                    lastRead.index
                )
            }
        )
    } else {
        ListItem(
            headlineContent = {
                Text(
                    text = if (isIndonesia) {
                        "Kamu belum membaca ayat apapun"
                    } else {
                        "You have not read any ayah yet"
                    },
                    style = MaterialTheme.typography.titleLarge
                )
            }
        )
    }
}

@Composable
private fun SurahBookmarkList(
    bookmarks: List<SurahBookmarkDomain>,
    lastRead: LastRead,
    selectedLanguage: Int,
    onOpenTypeDialog: () -> Unit,
    onDelete: (SurahBookmarkDomain) -> Unit,
    goToRead: (surahNumber: Int?, juzNumber: Int?, pageNumber: Int?, index: Int?) -> Unit
) {
    val isIndonesia = selectedLanguage == AppSettings.INDONESIA

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            LastReadSection(
                goToRead = goToRead,
                selectedLanguage = selectedLanguage,
                lastRead = lastRead
            )

            SectionHeader(
                title = if (isIndonesia) {
                    "Penanda Surat"
                } else {
                    "Surah Bookmarks"
                },
                onActionClick = onOpenTypeDialog,
                actionIcon = Icons.AutoMirrored.Filled.Sort
            )
        }

        items(bookmarks) { bookmark ->
            ListItem(
                headlineContent = {
                    Text(
                        text = "${bookmark.surahNameEn} | ${bookmark.surahNameAr}",
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                overlineContent = {
                    Text(
                        text = "Juz ${bookmark.juzNumber} | ${bookmark.surahDescend}",
                        style = MaterialTheme.typography.bodyLarge
                    )
                },
                supportingContent = {
                    Text(text = "${bookmark.totalAyah} Ayat")
                },
                leadingContent = {
                    NumberCard(number = "${bookmark.surahNumber}")
                },
                trailingContent = {
                    IconButton(onClick = { onDelete(bookmark) }) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = null
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 4.dp)
                    .clickable {
                        goToRead(
                            bookmark.surahNumber,
                            null,
                            null,
                            null
                        )
                    }
            )

            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 2.dp
            )
        }
    }
}

@Composable
private fun AyahBookmarkList(
    bookmarks: List<AyahBookmarkDomain>,
    onOpenTypeDialog: () -> Unit,
    onDelete: (AyahBookmarkDomain) -> Unit,
    lastRead: LastRead,
    selectedLanguage: Int,
    goToRead: (surahNumber: Int?, juzNumber: Int?, pageNumber: Int?, index: Int?) -> Unit
) {
    val isIndonesia = selectedLanguage == AppSettings.INDONESIA

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            LastReadSection(
                lastRead = lastRead,
                selectedLanguage = selectedLanguage,
                goToRead = goToRead,
            )

            SectionHeader(
                title = if (isIndonesia) {
                    "Penanda Ayat"
                } else {
                    "Ayah Bookmarks"
                },
                onActionClick = onOpenTypeDialog,
                actionIcon = Icons.AutoMirrored.Filled.Sort
            )
        }

        items(bookmarks) { bookmark ->
            ListItem(
                headlineContent = {
                    Text(
                        text = bookmark.surahName.orEmpty(),
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                supportingContent = {
                    Text(
                        text = "Ayat ${bookmark.ayahNumber}",
                        style = MaterialTheme.typography.bodyLarge
                    )
                },
                leadingContent = {
                    NumberCard(number = "${bookmark.surahNumber}")
                },
                trailingContent = {
                    IconButton(onClick = { onDelete(bookmark) }) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = null
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            )

            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                thickness = 2.dp
            )
        }
    }
}

@Composable
private fun SectionHeader(
    title: String,
    onActionClick: () -> Unit,
    actionIcon: ImageVector
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.align(Alignment.CenterStart)
        )

        IconButton(
            onClick = onActionClick,
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            Icon(
                imageVector = actionIcon,
                contentDescription = null
            )
        }
    }
}

@Composable
private fun NumberCard(number: String) {
    Card(
        modifier = Modifier
            .padding(12.dp)
            .size(34.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Box(Modifier.fillMaxSize()) {
            Text(
                text = number,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Composable
private fun BookmarkTypeDialog(
    selectedType: BookmarkType,
    onSelectedTypeChange: (BookmarkType) -> Unit,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    val options = listOf(
        BookmarkType.SURAH to "Surah",
        BookmarkType.AYAH to "Ayat"
    )

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Select Bookmarks") },
        text = {
            Column {
                options.forEach { (type, label) ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = type == selectedType,
                            onClick = { onSelectedTypeChange(type) }
                        )

                        TextButton(
                            onClick = { onSelectedTypeChange(type) }
                        ) {
                            Text(
                                text = label,
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text("Confirm")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Dismiss")
            }
        }
    )
}