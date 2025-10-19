package com.kecc.domain.usecases

import com.kecc.domain.interfaces.CoursesRepoItf

class DeleteCourseFromDb(val repo: CoursesRepoItf) {
    suspend fun execute(itemId: Int) {
        repo.deleteCourseFromDb(itemId)
    }
}