package com.example.ecology.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter

@Entity(tableName = "reports")
data class ReportEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val district: String,
    val street: String,
    val house: String,
    val description: String,
    val photo: String?
)

class Converters {
    @TypeConverter
    fun fromList(list: List<String>): String =
        list.joinToString(",")

    @TypeConverter
    fun toList(data: String): List<String> =
        if (data.isEmpty()) emptyList() else data.split(",")
}

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nickname: String,
    val email: String,
    val password: String,
    val isSubscription: Boolean
)