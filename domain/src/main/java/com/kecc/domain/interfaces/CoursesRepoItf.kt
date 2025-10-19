package com.kecc.domain.interfaces

import com.kecc.domain.models.CourseModel

interface CoursesRepoItf {
    suspend fun getCourses(): ArrayList<CourseModel>

    suspend fun insertCourseToDb(model: CourseModel)
    suspend fun getCoursesDb(): ArrayList<CourseModel>
}