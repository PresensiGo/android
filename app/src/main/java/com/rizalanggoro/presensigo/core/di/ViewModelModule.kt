package com.rizalanggoro.presensigo.core.di

import com.rizalanggoro.presensigo.presentation.attendance.AttendanceViewModel
import com.rizalanggoro.presensigo.presentation.attendance.create.CreateAttendanceViewModel
import com.rizalanggoro.presensigo.presentation.attendance.detail.DetailAttendanceViewModel
import com.rizalanggoro.presensigo.presentation.classroom.ClassroomViewModel
import com.rizalanggoro.presensigo.presentation.lateness.detail.DetailLatenessViewModel
import com.rizalanggoro.presensigo.presentation.lateness.detail.create.CreateDetailLatenessViewModel
import com.rizalanggoro.presensigo.presentation.pages.auth.AuthViewModel
import com.rizalanggoro.presensigo.presentation.pages.home.HomeViewModel
import com.rizalanggoro.presensigo.presentation.pages.home.attendance.HomeAttendanceViewModel
import com.rizalanggoro.presensigo.presentation.pages.home.lateness.HomeLatenessViewModel
import com.rizalanggoro.presensigo.presentation.pages.home.setting.HomeSettingViewModel
import com.rizalanggoro.presensigo.presentation.pages.home.student.StudentHomeViewModel
import com.rizalanggoro.presensigo.presentation.student.StudentViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::AuthViewModel)
    viewModelOf(::ClassroomViewModel)
    viewModelOf(::StudentViewModel)

    viewModelOf(::HomeViewModel)
    viewModelOf(::StudentHomeViewModel)
    viewModelOf(::HomeAttendanceViewModel)
    viewModelOf(::HomeLatenessViewModel)
    viewModelOf(::HomeSettingViewModel)

    viewModelOf(::AttendanceViewModel)
    viewModelOf(::CreateAttendanceViewModel)
    viewModelOf(::DetailAttendanceViewModel)

    viewModelOf(::DetailLatenessViewModel)
    viewModelOf(::CreateDetailLatenessViewModel)
}