package com.kecc.domain.interfaces

import com.kecc.domain.models.CourseModel

interface CoursesRepoItf {
    suspend fun getCourses(): ArrayList<CourseModel>
}