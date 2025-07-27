package com.rizalanggoro.presensigo.core.di

import com.rizalanggoro.presensigo.presentation.attendance.AttendanceViewModel
import com.rizalanggoro.presensigo.presentation.attendance.create.CreateAttendanceViewModel
import com.rizalanggoro.presensigo.presentation.attendance.detail.DetailAttendanceViewModel
import com.rizalanggoro.presensigo.presentation.auth.AuthViewModel
import com.rizalanggoro.presensigo.presentation.classroom.ClassroomViewModel
import com.rizalanggoro.presensigo.presentation.home.HomeViewModel
import com.rizalanggoro.presensigo.presentation.home.attendance.HomeAttendanceViewModel
import com.rizalanggoro.presensigo.presentation.home.setting.HomeSettingViewModel
import com.rizalanggoro.presensigo.presentation.student.StudentViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::AuthViewModel)
    viewModelOf(::ClassroomViewModel)
    viewModelOf(::StudentViewModel)

    viewModelOf(::HomeViewModel)
    viewModelOf(::HomeAttendanceViewModel)
    viewModelOf(::HomeSettingViewModel)

    viewModelOf(::AttendanceViewModel)
    viewModelOf(::CreateAttendanceViewModel)
    viewModelOf(::DetailAttendanceViewModel)
}