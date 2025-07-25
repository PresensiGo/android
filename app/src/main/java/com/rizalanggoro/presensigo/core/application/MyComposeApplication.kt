package com.rizalanggoro.presensigo.core.application

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rizalanggoro.presensigo.core.Routes
import com.rizalanggoro.presensigo.core.compositional.LocalNavController
import com.rizalanggoro.presensigo.presentation.attendance.AttendanceScreen
import com.rizalanggoro.presensigo.presentation.attendance.create.CreateAttendanceScreen
import com.rizalanggoro.presensigo.presentation.auth.AuthScreen
import com.rizalanggoro.presensigo.presentation.classroom.ClassroomScreen
import com.rizalanggoro.presensigo.presentation.home.HomeScreen
import com.rizalanggoro.presensigo.presentation.setting.SettingScreen
import com.rizalanggoro.presensigo.presentation.student.StudentScreen
import com.rizalanggoro.presensigo.ui.theme.PresensiGoTheme

@Composable
fun MyComposeApplication(isAuthenticated: Boolean = false) {
    PresensiGoTheme(darkTheme = false) {
        val navController = rememberNavController()

        CompositionLocalProvider(LocalNavController provides navController) {
            NavHost(
                navController = navController,
                startDestination = when (isAuthenticated) {
                    true -> Routes.Attendance.Create(classroomId = 2)
                    else -> Routes.Auth
                }
            ) {
                composable<Routes.Auth> { AuthScreen() }
                composable<Routes.Home> { HomeScreen() }
                composable<Routes.Classroom> { ClassroomScreen() }
                composable<Routes.Student> { StudentScreen() }
                composable<Routes.Setting> { SettingScreen() }
                composable<Routes.Attendance.List> { AttendanceScreen() }
                composable<Routes.Attendance.Create> { CreateAttendanceScreen() }
            }
        }
    }
}