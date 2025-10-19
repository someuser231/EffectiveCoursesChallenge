package com.kecc.data.repo

import com.kecc.data.interfaces.CoursesApiItf
import com.kecc.data.local.MainDb
import com.kecc.data.remote.CoursesApiUtils
import com.kecc.domain.interfaces.CoursesRepoItf
import com.kecc.domain.interfaces.DisplayableItemItf
import com.kecc.domain.models.CourseModel

class CoursesRepoImp(val db: MainDb): CoursesRepoItf {
    override suspend fun getCourses(): ArrayList<CourseModel> {
        val retrofitInstance = CoursesApiUtils.getInstance()
        val coursesApi = retrofitInstance.create(CoursesApiItf::class.java)
        return coursesApi.getCourses().courses
    }

    override suspend fun insertCourseToDb(model: CourseModel) {
        db.getCourseDao().insertItem(db.toDbItem(model, true))
    }

    override suspend fun getCoursesDb(): ArrayList<CourseModel> {
        val items = db.getCourseDao().getItems()
        val result = ArrayList<CourseModel>()
        for (i in items) {
            result.add(db.toModel(i))
        }
        return result
    }
}