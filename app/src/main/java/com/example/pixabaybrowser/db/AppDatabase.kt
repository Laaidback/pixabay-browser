package com.example.pixabaybrowser.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.core.db.Converters
import com.example.imagedatasource.data.db.ImageDao
import com.example.imagedatasource.data.db.ImageEntity
import com.example.imagedatasource.data.db.SearchResultEntity

private const val DATABASE_VERSION = 1

@Database(
    entities = [
        ImageEntity::class,
        SearchResultEntity::class,
    ],
    version = DATABASE_VERSION,
    exportSchema = false,
)
@TypeConverters(value = [Converters::class])
abstract class AppDatabase : RoomDatabase() {

    abstract fun imageDao(): ImageDao
}
