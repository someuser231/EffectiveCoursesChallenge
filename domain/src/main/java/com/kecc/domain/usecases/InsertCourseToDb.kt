package com.kecc.domain.usecases

import com.kecc.domain.interfaces.CoursesRepoItf
import com.kecc.domain.models.CourseModel

class InsertCourseToDb(val repo: CoursesRepoItf) {
    suspend fun execute(model: CourseModel) {
        repo.insertCourseToDb(model)
    }
}