package com.olimhousestudio.qooraan.presentation.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.olimhousestudio.qooraan.data.datasource.local.QoranDatabase
import com.olimhousestudio.qooraan.presentation.component.SurahFavoriteButton

@Composable
fun SurahScreens(
    modifier: Modifier = Modifier,
    goToRead: (surahNumber: Int?, juzNumber: Int?, pageNumber: Int?, index: Int?) -> Unit
) {

    val context = LocalContext.current
    val dao = QoranDatabase.getInstance(context).dao()
    val list = dao.getSurah()

    list.collectAsState(initial = emptyList()).let { state ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
        ) {
            items(state.value) { surah ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10))
//                    .padding(horizontal = 16.dp, vertical = 8.dp)
                        .clickable {
                            goToRead.invoke(surah.surahNumber, null, null, null)
                        },
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.background
                    )

                ) {
                    ListItem(
                        headlineContent = {
                            Text(
                                text = "${surah.surahNameEn} | ${surah.surahNameAr}",
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.titleLarge
                            )
                        },
                        leadingContent = {
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
                                        text = "${surah.surahNumber}",
                                        style = MaterialTheme.typography.bodyLarge,
                                        modifier = Modifier.align(Alignment.Center)
                                    )
                                }
                            }
                        },
                        supportingContent = {
                            Text(
                                text = "${surah.numberOfAyah} Ayat | Juz ${surah.juzNumber} | ${surah.surahDescendPlace}",
                                style = MaterialTheme.typography.titleMedium
                            )
                        },
                        trailingContent = {
                            SurahFavoriteButton(
                                modifier = Modifier,
                                totalAyah = surah.numberOfAyah,
                                surahNumber = surah.surahNumber,
                                juzNumber = surah.juzNumber,
                                surahNameEn = surah.surahNameEn,
                                surahNameAr = surah.surahNameAr,
                                surahDescend = surah.surahDescendPlace,
                            )
                        }
                    )
                    HorizontalDivider(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        thickness = 2.dp,
                    )
                }
            }
        }
    }
}
//
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.runtime.*
//import androidx.lifecycle.viewmodel.compose.viewModel
//import androidx.compose.material3.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.platform.LocalContext
//import com.olimhousestudio.qooraan.presentation.viewmodel.surahviewmodel.SurahViewModel
//import com.olimhousestudio.qooraan.presentation.viewmodel.surahviewmodel.SurahViewModelFactory
//
//@Composable
//fun SurahScreens(
//    modifier: Modifier = Modifier,
//    goToRead: (surahNumber: Int?, juzNumber: Int?, pageNumber: Int?, index: Int?) -> Unit,
//    surahViewModel: SurahViewModel = viewModel(factory = SurahViewModelFactory(QoranDatabase.getInstance(LocalContext.current).dao()))
//) {
//    // Collecting the state of surahs from the ViewModel
//    val surahs by surahViewModel.surahs.collectAsState()
//
//    LazyColumn(
//        modifier = modifier.fillMaxSize()
//    ) {
//        items(surahs) { surah ->
//            Card(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .clip(RoundedCornerShape(10))
//                    .clickable {
//                        goToRead.invoke(surah.surahNumber, null, null, null)
//                    },
//                colors = CardDefaults.cardColors(
//                    containerColor = MaterialTheme.colorScheme.background
//                )
//            ) {
//                ListItem(
//                    headlineContent = {
//                        Text(
//                            text = "${surah.surahNameEn} | ${surah.surahNameAr}",
//                            fontWeight = FontWeight.Bold,
//                            style = MaterialTheme.typography.titleLarge
//                        )
//                    },
//                    leadingContent = {
//                        Card(
//                            modifier = Modifier
//                                .padding(12.dp)
//                                .size(34.dp),
//                            colors = CardDefaults.cardColors(
//                                containerColor = MaterialTheme.colorScheme.surfaceVariant
//                            )
//                        ) {
//                            Box(Modifier.fillMaxSize()) {
//                                Text(
//                                    text = "${surah.surahNumber}",
//                                    style = MaterialTheme.typography.bodyLarge,
//                                    modifier = Modifier.align(Alignment.Center)
//                                )
//                            }
//                        }
//                    },
//                    supportingContent = {
//                        Text(
//                            text = "${surah.numberOfAyah} Ayat | Juz ${surah.juzNumber} | ${surah.surahDescendPlace}",
//                            style = MaterialTheme.typography.titleMedium
//                        )
//                    },
//                    trailingContent = {
//                        SurahFavoriteButton(
//                            modifier = Modifier,
//                            totalAyah = surah.numberOfAyah,
//                            surahNumber = surah.surahNumber,
//                            juzNumber = surah.juzNumber,
//                            surahNameEn = surah.surahNameEn,
//                            surahNameAr = surah.surahNameAr,
//                            surahDescend = surah.surahDescendPlace,
//                        )
//                    }
//                )
//                HorizontalDivider(
//                    modifier = Modifier.padding(horizontal = 16.dp),
//                    thickness = 2.dp,
//                )
//            }
//        }
//    }
//}
