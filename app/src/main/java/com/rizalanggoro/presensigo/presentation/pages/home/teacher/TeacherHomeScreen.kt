package com.rizalanggoro.presensigo.presentation.pages.home.teacher

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Checklist
import androidx.compose.material.icons.filled.MoreTime
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Checklist
import androidx.compose.material.icons.outlined.MoreTime
import androidx.compose.material.icons.outlined.Settings
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
import com.rizalanggoro.presensigo.core.Routes
import com.rizalanggoro.presensigo.presentation.pages.home.teacher.general.TeacherHomeGeneralAttendanceScreen
import com.rizalanggoro.presensigo.presentation.pages.home.teacher.setting.TeacherHomeSettingScreen
import com.rizalanggoro.presensigo.presentation.pages.home.teacher.subject.TeacherHomeSubjectAttendanceScreen

private data class NavigationItem<T : Any>(
    val title: String,
    val icon: ImageVector,
    val iconSelected: ImageVector,
    val route: T
)

private val navigationItems = listOf(
    NavigationItem(
        title = "Mata Pelajaran",
        icon = Icons.Outlined.Checklist,
        iconSelected = Icons.Filled.Checklist,
        route = Routes.Home.Teacher.SubjectAttendance
    ),
    NavigationItem(
        title = "Kehadiran",
        icon = Icons.Outlined.MoreTime,
        iconSelected = Icons.Filled.MoreTime,
        route = Routes.Home.Teacher.GeneralAttendance
    ),
    NavigationItem(
        title = "Pengaturan",
        icon = Icons.Outlined.Settings,
        iconSelected = Icons.Filled.Settings,
        route = Routes.Home.Teacher.Setting
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeacherHomeScreen() {
    val homeNavController = rememberNavController()
    val navBackStackEntry by homeNavController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        contentWindowInsets = WindowInsets(top = 0.dp),
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
                        icon = {
                            Icon(
                                when (isSelected) {
                                    true -> item.iconSelected
                                    else -> item.icon
                                }, contentDescription = null
                            )
                        },
                        label = { Text(item.title) }
                    )
                }
            }
        }
    ) {
        NavHost(
            modifier = Modifier.padding(it),
            navController = homeNavController,
            startDestination = Routes.Home.Teacher.SubjectAttendance
        ) {
            composable<Routes.Home.Teacher.SubjectAttendance> { TeacherHomeSubjectAttendanceScreen() }
            composable<Routes.Home.Teacher.GeneralAttendance> { TeacherHomeGeneralAttendanceScreen() }
            composable<Routes.Home.Teacher.Setting> { TeacherHomeSettingScreen() }
        }
    }
}