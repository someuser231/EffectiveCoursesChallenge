package com.kecc.data.repo

import com.kecc.data.interfaces.CoursesApiItf
import com.kecc.data.remote.CoursesApiUtils
import com.kecc.domain.interfaces.CoursesRepoItf
import com.kecc.domain.models.CourseModel

class CoursesRepoImp: CoursesRepoItf {
    override suspend fun getCourses(): ArrayList<CourseModel> {
        val retrofitInstance = CoursesApiUtils.getInstance()
        val coursesApi = retrofitInstance.create(CoursesApiItf::class.java)
        return coursesApi.getCourses().courses
    }
}