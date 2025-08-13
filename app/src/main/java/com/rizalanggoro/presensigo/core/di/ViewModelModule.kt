package com.rizalanggoro.presensigo.core.di

import com.rizalanggoro.presensigo.presentation.attendance.AttendanceViewModel
import com.rizalanggoro.presensigo.presentation.attendance.create.CreateAttendanceViewModel
import com.rizalanggoro.presensigo.presentation.attendance.detail.DetailAttendanceViewModel
import com.rizalanggoro.presensigo.presentation.lateness.detail.DetailLatenessViewModel
import com.rizalanggoro.presensigo.presentation.lateness.detail.create.CreateDetailLatenessViewModel
import com.rizalanggoro.presensigo.presentation.pages.attendance.general.create.CreateGeneralAttendanceViewModel
import com.rizalanggoro.presensigo.presentation.pages.attendance.general.detail.DetailGeneralAttendanceViewModel
import com.rizalanggoro.presensigo.presentation.pages.attendance.subject.classroom.ClassroomViewModel
import com.rizalanggoro.presensigo.presentation.pages.attendance.subject.create.CreateSubjectAttendanceViewModel
import com.rizalanggoro.presensigo.presentation.pages.attendance.subject.detail.DetailSubjectAttendanceViewModel
import com.rizalanggoro.presensigo.presentation.pages.attendance.subject.index.SubjectAttendanceViewModel
import com.rizalanggoro.presensigo.presentation.pages.attendance.subject.major.MajorViewModel
import com.rizalanggoro.presensigo.presentation.pages.auth.AuthViewModel
import com.rizalanggoro.presensigo.presentation.pages.home.student.StudentHomeViewModel
import com.rizalanggoro.presensigo.presentation.pages.home.teacher.TeacherHomeViewModel
import com.rizalanggoro.presensigo.presentation.pages.home.teacher.general.TeacherHomeGeneralAttendanceViewModel
import com.rizalanggoro.presensigo.presentation.pages.home.teacher.setting.TeacherHomeSettingViewModel
import com.rizalanggoro.presensigo.presentation.pages.home.teacher.subject.TeacherHomeSubjectAttendanceViewModel
import com.rizalanggoro.presensigo.presentation.student.StudentViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    // attendance - subject
    viewModelOf(::MajorViewModel)
    viewModelOf(::ClassroomViewModel)
    viewModelOf(::SubjectAttendanceViewModel)
    viewModelOf(::DetailSubjectAttendanceViewModel)
    viewModelOf(::CreateSubjectAttendanceViewModel)

    // attendance - general
    viewModelOf(::DetailGeneralAttendanceViewModel)
    viewModelOf(::CreateGeneralAttendanceViewModel)

    // auth
    viewModelOf(::AuthViewModel)

    // home - teacher
    viewModelOf(::TeacherHomeViewModel)
    viewModelOf(::TeacherHomeSubjectAttendanceViewModel)
    viewModelOf(::TeacherHomeGeneralAttendanceViewModel)
    viewModelOf(::TeacherHomeSettingViewModel)

    // home - student
    viewModelOf(::StudentHomeViewModel)

    viewModelOf(::StudentViewModel)


    viewModelOf(::AttendanceViewModel)
    viewModelOf(::CreateAttendanceViewModel)
    viewModelOf(::DetailAttendanceViewModel)

    viewModelOf(::DetailLatenessViewModel)
    viewModelOf(::CreateDetailLatenessViewModel)
}