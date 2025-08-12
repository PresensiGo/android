package com.rizalanggoro.presensigo.core.application

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.rizalanggoro.presensigo.core.Routes
import com.rizalanggoro.presensigo.core.compositional.LocalNavController
import com.rizalanggoro.presensigo.domain.TokenType
import com.rizalanggoro.presensigo.presentation.attendance.AttendanceScreen
import com.rizalanggoro.presensigo.presentation.attendance.create.CreateAttendanceScreen
import com.rizalanggoro.presensigo.presentation.attendance.detail.DetailAttendanceScreen
import com.rizalanggoro.presensigo.presentation.classroom.ClassroomScreen
import com.rizalanggoro.presensigo.presentation.lateness.detail.DetailLatenessScreen
import com.rizalanggoro.presensigo.presentation.lateness.detail.create.CreateDetailLatenessScreen
import com.rizalanggoro.presensigo.presentation.pages.auth.AuthScreen
import com.rizalanggoro.presensigo.presentation.pages.home.HomeScreen
import com.rizalanggoro.presensigo.presentation.pages.home.student.StudentHomeScreen
import com.rizalanggoro.presensigo.presentation.pages.qrscanner.QRScannerScreen
import com.rizalanggoro.presensigo.presentation.student.StudentScreen
import com.rizalanggoro.presensigo.ui.theme.PresensiGoTheme

@Composable
fun MyComposeApplication(
    isAuthenticated: Boolean = false,
    tokenType: TokenType = TokenType.Unset
) {
    PresensiGoTheme(darkTheme = true) {
        Surface {
            val navController = rememberNavController()

            CompositionLocalProvider(LocalNavController provides navController) {
                NavHost(
                    navController = navController,
                    startDestination = when (isAuthenticated) {
//                        true -> Routes.Lateness.Detail.Create(latenessId = 1)
                        true -> when (tokenType) {
                            TokenType.Unset -> Routes.Auth
                            TokenType.Teacher -> Routes.Home
                            TokenType.Student -> Routes.StudentHome
                        }

                        else -> Routes.Auth
                    }
                ) {
                    composable<Routes.Auth> { AuthScreen() }
                    composable<Routes.Home> { HomeScreen() }
                    composable<Routes.StudentHome> { StudentHomeScreen() }
                    composable<Routes.Classroom> { ClassroomScreen() }
                    composable<Routes.Student> { StudentScreen() }
                    composable<Routes.QrScanner> { QRScannerScreen() }

                    // attendance
                    composable<Routes.Attendance.Create> { CreateAttendanceScreen() }
                    composable<Routes.Attendance.List> {
                        AttendanceScreen(
                            classroomID = it.toRoute<Routes.Attendance.List>().classroomID
                        )
                    }
                    composable<Routes.Attendance.Detail> { DetailAttendanceScreen() }

                    // lateness
                    composable<Routes.Lateness.Detail.Index> { DetailLatenessScreen() }
                    composable<Routes.Lateness.Detail.Create> { CreateDetailLatenessScreen() }
                }
            }
        }
    }
}