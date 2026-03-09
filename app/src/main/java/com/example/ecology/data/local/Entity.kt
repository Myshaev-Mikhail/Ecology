package com.example.ecology.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter

@Entity(tableName = "reports")
data class ReportEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val userId: Int?,
    val district: String,
    val street: String,
    val house: String,
    val description: String,
    val photo: String?
)

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val role: UserRole,
    val nickname: String,
    val email: String,
    val password: String,
    val isSubscription: Boolean
)

enum class UserRole {
    MEDIA,
    REGULAR
}

class Converters {
    @TypeConverter
    fun fromUserRole(role: UserRole): String = role.name

    @TypeConverter
    fun toUserRole(role: String): UserRole = UserRole.valueOf(role)

    @TypeConverter
    fun fromList(list: List<String>): String =
        list.joinToString(",")

    @TypeConverter
    fun toList(data: String): List<String> =
        if (data.isEmpty()) emptyList() else data.split(",")
}