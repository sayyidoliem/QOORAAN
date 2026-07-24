package com.olimhousestudio.qooraan.presentation.view

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.olimhousestudio.qooraan.domain.model.AppSettings
import com.olimhousestudio.qooraan.presentation.viewmodel.settings.SettingsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedbackScreens(
    openDrawer: () -> Unit
) {
    val viewModel: SettingsViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()

    val isIndonesia = uiState.selectedLanguage == AppSettings.INDONESIA
    val context = LocalContext.current
    val developerEmail = "sayyid.olim12@gmail.com"

    var openDialog by remember {
        mutableStateOf(false)
    }

    var textSubject by rememberSaveable {
        mutableStateOf("")
    }

    var textMessage by rememberSaveable {
        mutableStateOf("")
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = if (isIndonesia) {
                            "Umpan Balik"
                        } else {
                            "Feedback"
                        }
                    )
                },
                navigationIcon = {
                    IconButton(onClick = openDrawer) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Text(
                text = if (isIndonesia) {
                    "Apakah Anda memiliki kritik, saran, atau umpan balik? Kirimkan pesan kepada kami."
                } else {
                    "Do you have any criticism, suggestions, or feedback? Send us a message."
                },
                modifier = Modifier.padding(16.dp),
                textAlign = TextAlign.Center
            )

            Button(
                onClick = {
                    openDialog = true
                }
            ) {
                Text(
                    text = if (isIndonesia) {
                        "Kirim Pesan"
                    } else {
                        "Send Message"
                    }
                )
            }
        }

        if (openDialog) {
            Dialog(
                properties = DialogProperties(
                    usePlatformDefaultWidth = false
                ),
                onDismissRequest = {
                    openDialog = false
                }
            ) {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(
                                    text = if (isIndonesia) {
                                        "Kirim E-mail kepada Developer"
                                    } else {
                                        "Send E-mail To Developer"
                                    }
                                )
                            },
                            navigationIcon = {
                                IconButton(
                                    onClick = {
                                        openDialog = false
                                    }
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Close,
                                        contentDescription = null
                                    )
                                }
                            }
                        )
                    }
                ) { dialogPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(dialogPadding)
                            .background(MaterialTheme.colorScheme.surface),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            verticalArrangement = Arrangement.SpaceAround,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            OutlinedTextField(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp),
                                value = developerEmail,
                                onValueChange = {},
                                readOnly = true,
                                label = {
                                    Text(
                                        text = if (isIndonesia) {
                                            "Kepada"
                                        } else {
                                            "To"
                                        }
                                    )
                                }
                            )

                            OutlinedTextField(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp),
                                value = textSubject,
                                onValueChange = {
                                    textSubject = it
                                },
                                placeholder = {
                                    Text(
                                        text = if (isIndonesia) {
                                            "contoh: bug, kritik, saran"
                                        } else {
                                            "example: bugs, criticism, suggestions"
                                        }
                                    )
                                },
                                label = {
                                    Text(
                                        text = if (isIndonesia) {
                                            "Perihal"
                                        } else {
                                            "Subject"
                                        }
                                    )
                                }
                            )

                            OutlinedTextField(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(2f)
                                    .padding(horizontal = 16.dp),
                                value = textMessage,
                                onValueChange = {
                                    textMessage = it
                                },
                                label = {
                                    Text(
                                        text = if (isIndonesia) {
                                            "Pesan"
                                        } else {
                                            "Message"
                                        }
                                    )
                                }
                            )

                            Button(
                                onClick = {
                                    val intent = Intent(Intent.ACTION_SEND).apply {
                                        putExtra(Intent.EXTRA_EMAIL, arrayOf(developerEmail))
                                        putExtra(Intent.EXTRA_SUBJECT, textSubject)
                                        putExtra(Intent.EXTRA_TEXT, textMessage)
                                        type = "message/rfc822"
                                    }

                                    context.startActivity(
                                        Intent.createChooser(
                                            intent,
                                            if (isIndonesia) {
                                                "Pilih aplikasi email"
                                            } else {
                                                "Choose an Email client"
                                            }
                                        )
                                    )
                                },
                                modifier = Modifier
                                    .align(Alignment.End)
                                    .padding(
                                        end = 16.dp,
                                        bottom = 16.dp,
                                        top = 8.dp
                                    )
                            ) {
                                Text(
                                    text = if (isIndonesia) {
                                        "Kirim"
                                    } else {
                                        "Send"
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}