package com.kecc.domain.usecases

import com.kecc.domain.interfaces.CoursesRepoItf
import com.kecc.domain.models.CourseModel

class GetCourses(val repo: CoursesRepoItf) {
    suspend fun execute(): ArrayList<CourseModel> {
        return repo.getCourses()
    }
}