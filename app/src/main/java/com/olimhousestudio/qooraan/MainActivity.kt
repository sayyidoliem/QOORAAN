package com.olimhousestudio.qooraan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTimeFilled
import androidx.compose.material.icons.filled.Feedback
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Navigation
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.olimhousestudio.qooraan.presentation.view.AppInfoScreens
import com.olimhousestudio.qooraan.presentation.view.AyatScreens
import com.olimhousestudio.qooraan.presentation.view.BookmarkScreens
import com.olimhousestudio.qooraan.presentation.view.DiscoverScreens
import com.olimhousestudio.qooraan.presentation.view.FeedbackScreens
import com.olimhousestudio.qooraan.presentation.view.FindQiblat
import com.olimhousestudio.qooraan.presentation.view.OnBoardingScreens
import com.olimhousestudio.qooraan.presentation.view.PrayerScreens
import com.olimhousestudio.qooraan.presentation.view.QuranScreens
import com.olimhousestudio.qooraan.presentation.view.SettingScreens
import com.olimhousestudio.qooraan.ui.theme.QOORAANAppTheme
import com.olimhousestudio.qooraan.utils.Screen
import com.olimhousestudio.qooraan.utils.SettingPreferences
import kotlinx.coroutines.launch
import com.olimhousestudio.qooraan.utils.GlobalState

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QOORAANAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val navController: NavHostController = rememberNavController()
                    val navBackStackEntry = navController.currentBackStackEntryAsState().value
                    val currentRoute: String? = navBackStackEntry?.destination?.route
                    var currentDestination = navBackStackEntry?.destination?.route

                    val navDrawerState = DrawerState(initialValue = DrawerValue.Closed)

                    val scope = rememberCoroutineScope()

                    ModalNavigationDrawer(
                        drawerState = navDrawerState,
                        gesturesEnabled = (currentRoute != Screen.Detail.route),
                        drawerContent = {
                            ModalDrawerSheet(drawerContainerColor = MaterialTheme.colorScheme.surface) {
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
                                        navigationdrawerList.map { item ->
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
                                                selected = currentDestination == item.route,
                                                onClick = {
                                                    currentDestination = item.route
                                                    navController.navigate(item.route) {
                                                        popUpTo(navController.graph.findStartDestination().id) {
                                                            saveState = true
                                                        }
                                                        restoreState = true
                                                        launchSingleTop = true
                                                    }
                                                },
                                                icon = {
                                                    Icon(
                                                        imageVector = item.icon,
                                                        contentDescription = ""
                                                    )
                                                }
                                            )
                                        }
                                        HorizontalDivider(
                                            modifier = Modifier.padding(
                                                horizontal = 24.dp,
                                                vertical = 16.dp
                                            ),
                                            thickness = 1.dp,
                                        )
                                        secondaryNavItemList.map { item ->
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
                                                selected = currentDestination == item.route,
                                                onClick = {
                                                    navController.navigate(item.route) {
                                                        popUpTo(navController.graph.findStartDestination().id) {
                                                            saveState = true
                                                        }
                                                        restoreState = true
                                                        launchSingleTop = true
                                                    }
                                                },
                                                icon = {
                                                    Icon(
                                                        imageVector = item.icon,
                                                        contentDescription = ""
                                                    )
                                                }
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    ) {
                        Scaffold(
                            modifier = Modifier,
                            //fitur buat playlist
                            /*
                            bottomBar = {
                                if (bottomNavItemList.any {
                                        it.route == currentRoute
                                    }
                                ) NavigationBar(
                                    modifier = Modifier.clip(
                                        RoundedCornerShape(
                                            topStart = 30.dp,
                                            topEnd = 30.dp
                                        )
                                    )
                                ) {
                                    bottomNavItemList.map { item: BottomNavItem ->
                                        NavigationBarItem(
                                            selected = currentRoute == item.route,//untuk membandingkan index
                                            onClick = {
                                                currentRoute = item.route
                                                navController.navigate(item.route) {
                                                    popUpTo(navController.graph.findStartDestination().id) {
                                                        saveState =
                                                            true //buat menyimpan state mutable di setiap screen
                                                    }
                                                    restoreState = true
                                                    launchSingleTop =
                                                        true//buat klo back, langsung keluar app(bukan ke home)
                                                }
                                            },
                                            icon = {
                                                Icon(
                                                    imageVector = item.iconBottom,
                                                    contentDescription = item.label
                                                )
                                            },
                                            label = { Text(text = item.label) },
                                        )
                                    }
                                }
                            },
                             */
                        ) { innerPadding ->
                            NavHost(
                                modifier = Modifier.padding(innerPadding),
                                navController = navController,
                                startDestination = if (SettingPreferences.isOnBoarding && GlobalState.isOnBoarding) Screen.OnBoarding.route else Screen.Read.route
                            ) {
                                composable(Screen.OnBoarding.route) {
                                    OnBoardingScreens(goNext = { navController.navigate(Screen.Read.route) })
                                }
                                composable(Screen.Prayer.route) {
                                    PrayerScreens(openDrawer = { scope.launch { navDrawerState.open() } })
                                }
                                composable(Screen.Discover.route) {
                                    DiscoverScreens(openDrawer = { scope.launch { navDrawerState.open() } })
                                }
                                composable(Screen.Read.route) {
                                    QuranScreens(
                                        goToRead = { surahNumber, juzNumber, pageNumber, index ->
                                            navController.navigate(
                                                Screen.Detail.createRoute(
                                                    surahNumber = surahNumber,
                                                    juzNumber = juzNumber,
                                                    pageNumber = pageNumber,
                                                    index = index
                                                )
                                            )
                                        },
                                        openDrawer = { scope.launch { navDrawerState.open() } }
                                    )
                                }
                                composable(Screen.Detail.route, arguments = listOf(
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
                                ) {
                                    val surahNumber = it.arguments?.getInt("surahNumber") ?: -1
                                    val juzNumber = it.arguments?.getInt("juzNumber") ?: -1
                                    val pageNumber = it.arguments?.getInt("pageNumber") ?: -1
                                    val index = it.arguments?.getInt("index") ?: -1
                                    AyatScreens(
                                        goBack = { navController.navigateUp() },
                                        surahNumber = surahNumber,
                                        juzNumber = juzNumber,
                                        pageNumber = pageNumber,
                                        index = index
                                    )
                                }
                                composable("bookmark") {
                                    BookmarkScreens(
                                        modifier = Modifier,
                                        goToRead = { surahNumber, juzNumber, pageNumber, index ->
                                            navController.navigate(
                                                Screen.Detail.createRoute(
                                                    surahNumber = surahNumber,
                                                    juzNumber = juzNumber,
                                                    pageNumber = pageNumber,
                                                    index = index
                                                )
                                            )
                                        },
                                    )
                                }
                                composable(Screen.Setting.route) {
                                    SettingScreens(openDrawer = { scope.launch { navDrawerState.open() } })
                                }
                                composable(Screen.Qiblat.route) {
                                    FindQiblat(openDrawer = { scope.launch { navDrawerState.open() } })
                                }
                                composable(Screen.Info.route) {
                                    AppInfoScreens(openDrawer = { scope.launch { navDrawerState.open() } })
                                }
                                composable(Screen.Feedback.route) {
                                    FeedbackScreens(openDrawer = { scope.launch { navDrawerState.open() } })
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}




data class BottomNavItem(
    val label: String,
    val iconBottom: ImageVector,
    val route: String
)

val bottomNavItemList: List<BottomNavItem> = listOf(
    BottomNavItem("Home", Icons.Default.Home, Screen.Read.route),
    BottomNavItem("Qori", Icons.Default.PlayCircle, Screen.Discover.route),
)

data class NavItem(
    val route: String,
    val label: String,
    val icon: ImageVector
)

val navigationdrawerList: List<NavItem> = listOf(
    NavItem(Screen.Read.route, "Quran", Icons.Default.Home),
    NavItem(Screen.Qiblat.route, "Qiblat Compass", Icons.Default.Navigation),
//    NavItem(Screen.Prayer.route, "Prayer Time", Icons.Default.AccessTimeFilled),
    NavItem(Screen.Setting.route, "Settings", Icons.Default.Settings)
)
val secondaryNavItemList: List<NavItem> = listOf(
//    NavItem(Screen.Feedback.route, "Feedback", Icons.Default.Feedback),
    NavItem(Screen.Info.route, "App Info", Icons.Default.Info)
)