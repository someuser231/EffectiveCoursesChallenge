package com.kecc.data.interfaces

import com.kecc.data.remote.CoursesApiResp
import retrofit2.http.GET

interface CoursesApiItf {
    @GET("u/0/uc?id=15arTK7XT2b7Yv4BJsmDctA4Hg-BbS8-q&export=download")
    suspend fun getCourses(): CoursesApiResp
}