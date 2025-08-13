package com.rizalanggoro.presensigo.core.di

import com.rizalanggoro.presensigo.presentation.attendance.AttendanceViewModel
import com.rizalanggoro.presensigo.presentation.attendance.create.CreateAttendanceViewModel
import com.rizalanggoro.presensigo.presentation.attendance.detail.DetailAttendanceViewModel
import com.rizalanggoro.presensigo.presentation.lateness.detail.DetailLatenessViewModel
import com.rizalanggoro.presensigo.presentation.lateness.detail.create.CreateDetailLatenessViewModel
import com.rizalanggoro.presensigo.presentation.pages.attendance.subject.classroom.ClassroomViewModel
import com.rizalanggoro.presensigo.presentation.pages.attendance.subject.detail.DetailSubjectAttendanceViewModel
import com.rizalanggoro.presensigo.presentation.pages.attendance.subject.index.SubjectAttendanceViewModel
import com.rizalanggoro.presensigo.presentation.pages.attendance.subject.major.MajorViewModel
import com.rizalanggoro.presensigo.presentation.pages.auth.AuthViewModel
import com.rizalanggoro.presensigo.presentation.pages.home.student.StudentHomeViewModel
import com.rizalanggoro.presensigo.presentation.pages.home.teacher.TeacherHomeViewModel
import com.rizalanggoro.presensigo.presentation.pages.home.teacher.general.TeacherHomeGeneralAttendanceViewModel
import com.rizalanggoro.presensigo.presentation.pages.home.teacher.setting.HomeSettingViewModel
import com.rizalanggoro.presensigo.presentation.pages.home.teacher.subject.TeacherHomeSubjectAttendanceViewModel
import com.rizalanggoro.presensigo.presentation.student.StudentViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    // attendance
    viewModelOf(::MajorViewModel)
    viewModelOf(::ClassroomViewModel)
    viewModelOf(::SubjectAttendanceViewModel)
    viewModelOf(::DetailSubjectAttendanceViewModel)

    // auth
    viewModelOf(::AuthViewModel)

    // home - teacher
    viewModelOf(::TeacherHomeViewModel)
    viewModelOf(::TeacherHomeSubjectAttendanceViewModel)
    viewModelOf(::TeacherHomeGeneralAttendanceViewModel)
    viewModelOf(::HomeSettingViewModel)

    // home - student
    viewModelOf(::StudentHomeViewModel)

    viewModelOf(::StudentViewModel)

//    viewModelOf(::HomeAttendanceViewModel)
//    viewModelOf(::HomeLatenessViewModel)

    viewModelOf(::AttendanceViewModel)
    viewModelOf(::CreateAttendanceViewModel)
    viewModelOf(::DetailAttendanceViewModel)

    viewModelOf(::DetailLatenessViewModel)
    viewModelOf(::CreateDetailLatenessViewModel)
}