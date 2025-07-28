package com.rizalanggoro.presensigo.presentation.home

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.composables.icons.lucide.ListChecks
import com.composables.icons.lucide.ListX
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.Settings
import com.rizalanggoro.presensigo.core.Routes
import com.rizalanggoro.presensigo.presentation.home.attendance.HomeAttendanceScreen
import com.rizalanggoro.presensigo.presentation.home.lateness.HomeLatenessScreen
import com.rizalanggoro.presensigo.presentation.home.setting.HomeSettingScreen

private data class NavigationItem<T : Any>(
    val title: String,
    val icon: ImageVector,
    val route: T
)

private val navigationItems = listOf(
    NavigationItem(
        title = "Presensi",
        icon = Lucide.ListChecks,
        route = Routes.Home.Attendance
    ),
    NavigationItem(
        title = "Keterlambatan",
        icon = Lucide.ListX,
        route = Routes.Home.Lateness
    ),
    NavigationItem(
        title = "Pengaturan",
        icon = Lucide.Settings,
        route = Routes.Home.Setting
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val homeNavController = rememberNavController()
    val navBackStackEntry by homeNavController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        contentWindowInsets = WindowInsets(
            top = 0.dp
        ),
        bottomBar = {
            NavigationBar {
                navigationItems.map { item ->
                    val isSelected = currentDestination?.hierarchy?.any {
                        it.hasRoute(item.route::class)
                    } == true

                    NavigationBarItem(
                        selected = isSelected,
                        onClick = {
                            homeNavController.navigate(item.route) {
                                popUpTo(homeNavController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = { Icon(item.icon, contentDescription = null) },
                        label = { Text(item.title) }
                    )
                }
            }
        }
    ) {
        NavHost(
            modifier = Modifier.padding(it),
            navController = homeNavController,
            startDestination = Routes.Home.Lateness
        ) {
            composable<Routes.Home.Attendance> { HomeAttendanceScreen() }
            composable<Routes.Home.Lateness> { HomeLatenessScreen() }
            composable<Routes.Home.Setting> { HomeSettingScreen() }
        }
    }
}