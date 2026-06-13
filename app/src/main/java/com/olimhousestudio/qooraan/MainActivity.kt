package com.olimhousestudio.qooraan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Navigation
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.olimhousestudio.qooraan.presentation.view.AppInfoScreens
import com.olimhousestudio.qooraan.presentation.view.DiscoverScreens
import com.olimhousestudio.qooraan.presentation.view.FeedbackScreens
import com.olimhousestudio.qooraan.presentation.view.FindQiblat
import com.olimhousestudio.qooraan.presentation.view.OnBoardingScreens
import com.olimhousestudio.qooraan.presentation.view.PrayerScreens
import com.olimhousestudio.qooraan.presentation.view.SettingScreens
import com.olimhousestudio.qooraan.presentation.view.ayat.AyatRoute
import com.olimhousestudio.qooraan.presentation.viewmodel.settings.SettingsViewModel
import com.olimhousestudio.qooraan.presentation.view.QuranScreens
import com.olimhousestudio.qooraan.ui.theme.QOORAANAppTheme
import com.olimhousestudio.qooraan.utils.Screen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val settingsViewModel: SettingsViewModel = hiltViewModel()
            val settingsState by settingsViewModel.uiState.collectAsState()

            QOORAANAppTheme(
                useDarkTheme = settingsState.isDarkMode
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if (!settingsState.isLoading) {
                        val navController: NavHostController = rememberNavController()
                        val navBackStackEntry by navController.currentBackStackEntryAsState()
                        val currentRoute = navBackStackEntry?.destination?.route

                        val drawerState = rememberDrawerState(
                            initialValue = DrawerValue.Closed
                        )

                        val scope = rememberCoroutineScope()

                        ModalNavigationDrawer(
                            drawerState = drawerState,
                            gesturesEnabled = currentRoute != Screen.Detail.route,
                            drawerContent = {
                                ModalDrawerSheet(
                                    drawerContainerColor = MaterialTheme.colorScheme.surface
                                ) {
                                    LazyColumn {
                                        item {
                                            Text(
                                                modifier = Modifier.padding(
                                                    start = 24.dp,
                                                    top = 24.dp,
                                                    bottom = 16.dp
                                                ),
                                                text = "QOORAAN",
                                                style = MaterialTheme.typography.headlineLarge,
                                                fontWeight = FontWeight.ExtraBold,
                                                color = MaterialTheme.colorScheme.primary,
                                                fontFamily = FontFamily(Font(R.font.brunoace))
                                            )

                                            mainDrawerItems.forEach { item ->
                                                NavigationDrawerItem(
                                                    modifier = Modifier.padding(
                                                        NavigationDrawerItemDefaults.ItemPadding
                                                    ),
                                                    label = {
                                                        Text(
                                                            text = item.label,
                                                            style = MaterialTheme.typography.titleLarge
                                                        )
                                                    },
                                                    selected = currentRoute == item.route,
                                                    onClick = {
                                                        scope.launch {
                                                            drawerState.close()
                                                        }

                                                        navController.navigate(item.route) {
                                                            popUpTo(
                                                                navController.graph.findStartDestination().id
                                                            ) {
                                                                saveState = true
                                                            }
                                                            restoreState = true
                                                            launchSingleTop = true
                                                        }
                                                    },
                                                    icon = {
                                                        Icon(
                                                            imageVector = item.icon,
                                                            contentDescription = item.label
                                                        )
                                                    }
                                                )
                                            }

                                            HorizontalDivider(
                                                modifier = Modifier.padding(
                                                    horizontal = 24.dp,
                                                    vertical = 16.dp
                                                ),
                                                thickness = 1.dp
                                            )

                                            secondaryDrawerItems.forEach { item ->
                                                NavigationDrawerItem(
                                                    modifier = Modifier.padding(
                                                        NavigationDrawerItemDefaults.ItemPadding
                                                    ),
                                                    label = {
                                                        Text(
                                                            text = item.label,
                                                            style = MaterialTheme.typography.titleLarge
                                                        )
                                                    },
                                                    selected = currentRoute == item.route,
                                                    onClick = {
                                                        scope.launch {
                                                            drawerState.close()
                                                        }

                                                        navController.navigate(item.route) {
                                                            popUpTo(
                                                                navController.graph.findStartDestination().id
                                                            ) {
                                                                saveState = true
                                                            }
                                                            restoreState = true
                                                            launchSingleTop = true
                                                        }
                                                    },
                                                    icon = {
                                                        Icon(
                                                            imageVector = item.icon,
                                                            contentDescription = item.label
                                                        )
                                                    }
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        ) {
                            Scaffold { innerPadding ->
                                NavHost(
                                    modifier = Modifier.padding(innerPadding),
                                    navController = navController,
                                    startDestination = if (settingsState.isOnBoarding) {
                                        Screen.OnBoarding.route
                                    } else {
                                        Screen.Read.route
                                    }
                                ) {
                                    composable(Screen.OnBoarding.route) {
                                        OnBoardingScreens(
                                            goNext = {
                                                settingsViewModel.setOnBoarding(false)

                                                navController.navigate(Screen.Read.route) {
                                                    popUpTo(Screen.OnBoarding.route) {
                                                        inclusive = true
                                                    }
                                                    launchSingleTop = true
                                                }
                                            }
                                        )
                                    }

                                    composable(Screen.Prayer.route) {
                                        PrayerScreens(
                                            openDrawer = {
                                                scope.launch {
                                                    drawerState.open()
                                                }
                                            }
                                        )
                                    }

                                    composable(Screen.Discover.route) {
                                        DiscoverScreens(
                                            openDrawer = {
                                                scope.launch {
                                                    drawerState.open()
                                                }
                                            }
                                        )
                                    }

                                    composable(Screen.Read.route) {
                                        QuranScreens(
                                            goToRead = { surahNumber, juzNumber, pageNumber, index ->
                                                navController.navigate(
                                                    Screen.Detail.createRoute(
                                                        surahNumber = surahNumber ?: -1,
                                                        juzNumber = juzNumber ?: -1,
                                                        pageNumber = pageNumber ?: -1,
                                                        index = index ?: -1
                                                    )
                                                )
                                            },
                                            openDrawer = {
                                                scope.launch {
                                                    drawerState.open()
                                                }
                                            }
                                        )
                                    }

                                    composable(
                                        route = Screen.Detail.route,
                                        arguments = listOf(
                                            navArgument("surahNumber") {
                                                type = NavType.IntType
                                                defaultValue = -1
                                            },
                                            navArgument("juzNumber") {
                                                type = NavType.IntType
                                                defaultValue = -1
                                            },
                                            navArgument("pageNumber") {
                                                type = NavType.IntType
                                                defaultValue = -1
                                            },
                                            navArgument("index") {
                                                type = NavType.IntType
                                                defaultValue = -1
                                            }
                                        )
                                    ) { backStackEntry ->
                                        AyatRoute(
                                            goBack = {
                                                navController.navigateUp()
                                            },
                                            surahNumber = backStackEntry.arguments?.getInt("surahNumber")
                                                ?: -1,
                                            juzNumber = backStackEntry.arguments?.getInt("juzNumber")
                                                ?: -1,
                                            pageNumber = backStackEntry.arguments?.getInt("pageNumber")
                                                ?: -1,
                                            index = backStackEntry.arguments?.getInt("index") ?: -1
                                        )
                                    }

                                    composable(Screen.Setting.route) {
                                        SettingScreens(
                                            openDrawer = {
                                                scope.launch {
                                                    drawerState.open()
                                                }
                                            }
                                        )
                                    }

                                    composable(Screen.Qiblat.route) {
                                        FindQiblat(
                                            openDrawer = {
                                                scope.launch {
                                                    drawerState.open()
                                                }
                                            }
                                        )
                                    }

                                    composable(Screen.Info.route) {
                                        AppInfoScreens(
                                            openDrawer = {
                                                scope.launch {
                                                    drawerState.open()
                                                }
                                            }
                                        )
                                    }

                                    composable(Screen.Feedback.route) {
                                        FeedbackScreens(
                                            openDrawer = {
                                                scope.launch {
                                                    drawerState.open()
                                                }
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
    }
}

private data class DrawerItem(
    val route: String,
    val label: String,
    val icon: ImageVector
)

private val mainDrawerItems = listOf(
    DrawerItem(
        route = Screen.Read.route,
        label = "Quran",
        icon = Icons.Default.Home
    ),
    DrawerItem(
        route = Screen.Qiblat.route,
        label = "Qiblat Compass",
        icon = Icons.Default.Navigation
    ),
    DrawerItem(
        route = Screen.Setting.route,
        label = "Settings",
        icon = Icons.Default.Settings
    )
)

private val secondaryDrawerItems = listOf(
    DrawerItem(
        route = Screen.Info.route,
        label = "App Info",
        icon = Icons.Default.Info
    )
)