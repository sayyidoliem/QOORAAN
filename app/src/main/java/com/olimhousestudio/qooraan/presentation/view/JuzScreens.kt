package com.olimhousestudio.qooraan.presentation.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.olimhousestudio.qooraan.data.datasource.local.QoranDatabase
import com.olimhousestudio.qooraan.utils.Converters.mapToJuzIndexing
import com.olimhousestudio.qooraan.presentation.component.JuzCardItem

@Composable
fun JuzScreens(
    modifier: Modifier = Modifier,
    goToRead: (surahNumber: Int?, juzNumber: Int?, pageNumber: Int?, index: Int?) -> Unit
) {
    val context = LocalContext.current
    val dao = QoranDatabase.getInstance(context).dao()
    val list = dao.getJuz()

    var expanded by remember { mutableStateOf(false) }

    list.collectAsState(initial = emptyList()).let { state ->
        val juzByIndexSurah = state.value.mapToJuzIndexing()
        LazyColumn(
            modifier = modifier.fillMaxSize()
        ) {
            items(juzByIndexSurah ?: emptyList()) { juzMap ->
                JuzCardItem(
                    juzNumber = juzMap.juzNumber!!,
                    surahList = juzMap.surahList,
                    surahNumberList = juzMap.surahNumberList,
                    goToRead = { surahNumber ->
                        goToRead(surahNumber, juzMap.juzNumber, null, null)
                    }
                )
            }
        }
    }
}
