package com.kecc.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kecc.domain.models.CourseModel

@Database(entities = [CourseDbItem::class], version = 1)
abstract class MainDb: RoomDatabase() {
    companion object {
        fun getDb(context: Context): MainDb =
            Room.databaseBuilder(
                context.applicationContext,
                MainDb::class.java,
                "data.db"
            ).build()
    }

    abstract fun getCourseDao(): CourseDao

    fun toDbItem(model: CourseModel, like: Boolean): CourseDbItem =
        CourseDbItem(
            id = model.id,
            title = model.title,
            text = model.text,
            price = model.price,
            rate = model.rate,
            startDate = model.startDate,
            hasLike = like,
            publishDate = model.publishDate,
        )

    fun toModel(item: CourseDbItem): CourseModel =
        CourseModel(
            id = item.id,
            title = item.title,
            text = item.text,
            price = item.price,
            rate = item.rate,
            startDate = item.startDate,
            hasLike = item.hasLike,
            publishDate = item.publishDate,
        )
}