package com.example.core.db

import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object Converters {

    @TypeConverter
    fun convert(list: List<String>): String {
        return Json.encodeToString(list)
    }

    @TypeConverter
    fun convert(encodedList: String): List<String> {
        return Json.decodeFromString(encodedList)
    }
}
