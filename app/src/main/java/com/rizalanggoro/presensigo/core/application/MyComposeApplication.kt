package com.rizalanggoro.presensigo.core.application

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rizalanggoro.presensigo.core.Routes
import com.rizalanggoro.presensigo.core.compositional.LocalNavController
import com.rizalanggoro.presensigo.domain.TokenType
import com.rizalanggoro.presensigo.presentation.pages.attendance.general.create.CreateGeneralAttendanceScreen
import com.rizalanggoro.presensigo.presentation.pages.attendance.general.detail.DetailGeneralAttendanceScreen
import com.rizalanggoro.presensigo.presentation.pages.attendance.subject.classroom.ClassroomScreen
import com.rizalanggoro.presensigo.presentation.pages.attendance.subject.create.CreateSubjectAttendanceScreen
import com.rizalanggoro.presensigo.presentation.pages.attendance.subject.detail.DetailSubjectAttendanceScreen
import com.rizalanggoro.presensigo.presentation.pages.attendance.subject.index.SubjectAttendanceScreen
import com.rizalanggoro.presensigo.presentation.pages.attendance.subject.major.MajorScreen
import com.rizalanggoro.presensigo.presentation.pages.auth.AuthScreen
import com.rizalanggoro.presensigo.presentation.pages.home.student.StudentHomeScreen
import com.rizalanggoro.presensigo.presentation.pages.home.teacher.TeacherHomeScreen
import com.rizalanggoro.presensigo.presentation.pages.qrscanner.QRScannerScreen
import com.rizalanggoro.presensigo.presentation.student.StudentScreen
import com.rizalanggoro.presensigo.ui.theme.PresensiGoTheme

@Composable
fun MyComposeApplication(
    isAuthenticated: Boolean = false,
    tokenType: TokenType = TokenType.Unset
) {
    PresensiGoTheme(darkTheme = false) {
        Surface {
            val navController = rememberNavController()

            CompositionLocalProvider(LocalNavController provides navController) {
                NavHost(
                    navController = navController,
                    startDestination = when (isAuthenticated) {
//                        true -> when (tokenType) {
//                            TokenType.Unset -> Routes.Auth
//                            TokenType.Teacher -> Routes.Home.Teacher
//                            TokenType.Student -> Routes.Home.Student
//                        }
                        true -> Routes.Attendance.General.Detail(
//                            batchId = 3,
//                            majorId = 6,
//                            classroomId = 14,
                            attendanceId = 1
                        )

                        else -> Routes.Auth
                    },
                    enterTransition = {
                        slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Start,
                        )
                    },
                    exitTransition = {
                        slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.Start,
                        )
                    },
                    popEnterTransition = {
                        slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.End,
                        )
                    },
                    popExitTransition = {
                        slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.End,
                        )
                    }
                ) {
                    // auth
                    composable<Routes.Auth> { AuthScreen() }

                    // home
                    composable<Routes.Home.Teacher> { TeacherHomeScreen() }
                    composable<Routes.Home.Student> { StudentHomeScreen() }

                    // attendance - subject
                    composable<Routes.Attendance.Subject.Major> { MajorScreen() }
                    composable<Routes.Attendance.Subject.Classroom> { ClassroomScreen() }
                    composable<Routes.Attendance.Subject.Index> { SubjectAttendanceScreen() }
                    composable<Routes.Attendance.Subject.Detail> { DetailSubjectAttendanceScreen() }
                    composable<Routes.Attendance.Subject.Create> { CreateSubjectAttendanceScreen() }

                    // attendance - general
                    composable<Routes.Attendance.General.Detail> { DetailGeneralAttendanceScreen() }
                    composable<Routes.Attendance.General.Create> { CreateGeneralAttendanceScreen() }

                    composable<Routes.Student> { StudentScreen() }
                    composable<Routes.QrScanner> { QRScannerScreen() }

                    // attendance
//                    composable<Routes.Attendance.Create> { CreateAttendanceScreen() }
//                    composable<Routes.Attendance.List> {
//                        AttendanceScreen(
//                            classroomID = it.toRoute<Routes.Attendance.List>().classroomID
//                        )
//                    }
//                    composable<Routes.Attendance.Detail> { DetailAttendanceScreen() }

                    // lateness
//                    composable<Routes.Lateness.Detail.Index> { DetailLatenessScreen() }
//                    composable<Routes.Lateness.Detail.Create> { CreateDetailLatenessScreen() }
                }
            }
        }
    }
}