package com.examplepart.foodpart.database.common

import androidx.room.TypeConverter

class Converter {
    @TypeConverter
    fun fromStringList(arr: List<String>): String {
        return arr.joinToString()
    }

    @TypeConverter
    fun toStringList(str: String): List<String> {
        return str.split(",").map {
            it.trim()
        }
    }
}