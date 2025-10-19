package com.kecc.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CourseDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertItem(item: CourseDbItem)

    @Query("select * from courses")
    suspend fun getItems(): List<CourseDbItem>
}