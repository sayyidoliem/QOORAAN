package com.olimhousestudio.qooraan.presentation.view

import android.app.Activity
import android.content.Intent
import android.view.ViewGroup
import android.widget.LinearLayout
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
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.just.agentweb.AgentWeb

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FindQiblat(
    openDrawer: () -> Unit
) {
    val context = LocalContext.current
    val activity = context as? Activity ?: return

    val qiblaUrl = "https://qiblafinder.withgoogle.com/intl/id/"
    val developerEmail = "sayyid.olim12@gmail.com"

    var expanded by remember {
        mutableStateOf(false)
    }

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
                        text = "Qiblat",
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                },
                navigationIcon = {
                    IconButton(onClick = openDrawer) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                actions = {
                    IconButton(
                        onClick = {
                            expanded = true
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }

                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = {
                            expanded = false
                        }
                    ) {
                        DropdownMenuItem(
                            text = {
                                Text("Share")
                            },
                            onClick = {
                                val sendIntent = Intent().apply {
                                    action = Intent.ACTION_SEND
                                    putExtra(
                                        Intent.EXTRA_TEXT,
                                        "Qiblat Finder by: $qiblaUrl"
                                    )
                                    type = "text/plain"
                                }

                                val shareIntent = Intent.createChooser(
                                    sendIntent,
                                    null
                                )

                                context.startActivity(shareIntent)
                                expanded = false
                            },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Outlined.Share,
                                    contentDescription = null
                                )
                            }
                        )

                        DropdownMenuItem(
                            text = {
                                Text("Send Feedback")
                            },
                            onClick = {
                                openDialog = true
                                expanded = false
                            },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Outlined.Email,
                                    contentDescription = null
                                )
                            },
                            trailingIcon = {
                                Text(
                                    text = "F11",
                                    textAlign = TextAlign.Center
                                )
                            }
                        )
                    }
                }
            )
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            AndroidView(
                modifier = Modifier.fillMaxSize(),
                factory = { androidContext ->
                    LinearLayout(androidContext).apply {
                        layoutParams = ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT
                        )

                        AgentWeb.with(activity)
                            .setAgentWebParent(this, layoutParams)
                            .useDefaultIndicator()
                            .createAgentWeb()
                            .ready()
                            .go(qiblaUrl)
                    }
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
                            Text(text = "Send E-mail To Developer")
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
                        },
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
                                Text("To")
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
                                Text(text = "example: bugs, criticism, suggestions")
                            },
                            label = {
                                Text("Subject")
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
                                Text("Message")
                            }
                        )

                        Button(
                            onClick = {
                                val intent = Intent(Intent.ACTION_SEND).apply {
                                    putExtra(
                                        Intent.EXTRA_EMAIL,
                                        arrayOf(developerEmail)
                                    )
                                    putExtra(Intent.EXTRA_SUBJECT, textSubject)
                                    putExtra(Intent.EXTRA_TEXT, textMessage)
                                    type = "message/rfc822"
                                }

                                context.startActivity(
                                    Intent.createChooser(
                                        intent,
                                        "Choose an Email client"
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
                            Text("Send")
                        }
                    }
                }
            }
        }
    }
}