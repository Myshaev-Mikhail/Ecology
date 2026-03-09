package com.example.ecology.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ReportDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(report: ReportEntity)

    // Все карточки
    @Query("SELECT * FROM reports ORDER BY id DESC")
    fun observeAllReports(): Flow<List<ReportEntity>>

    // Карточки конкретного пользователя (для экрана "Мои")
    @Query("SELECT * FROM reports WHERE userId = :userId ORDER BY id DESC")
    fun observeUserReports(userId: Int): Flow<List<ReportEntity>>

    // Чужие карточки (для медийных пользователей)
    @Query("SELECT * FROM reports WHERE userId != :userId ORDER BY id DESC")
    fun observeOtherReports(userId: Int): Flow<List<ReportEntity>>

    // Карточки гостей (незарегистрированных пользователей)
    @Query("SELECT * FROM reports WHERE userId IS NULL ORDER BY id DESC")
    fun observeGuestReports(): Flow<List<ReportEntity>>
}

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: UserEntity): Long

    // Проверка есть ли пользователь
    @Query("SELECT EXISTS(SELECT * FROM user)")
    fun isNotEmpty(): Flow<Boolean>

    // Логин
    @Query("SELECT * FROM user WHERE email = :email AND password = :password LIMIT 1")
    suspend fun login(email: String, password: String): UserEntity?

    // Получить пользователя по id
    @Query("SELECT * FROM user WHERE id = :id")
    suspend fun getUserById(id: Int): UserEntity?

    // Получить всех пользователей
    @Query("SELECT * FROM user")
    fun observeUsers(): Flow<List<UserEntity>>
}