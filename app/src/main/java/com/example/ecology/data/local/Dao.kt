package com.example.ecology.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ReportDao {
    @Insert
    suspend fun insert(report: ReportEntity)

    @Query("SELECT * FROM reports")
    fun observeReports(): Flow<List<ReportEntity>>
}

@Dao
interface UserDao {
    @Insert
    suspend fun insert(user: UserEntity)

    @Query("SELECT EXISTS(SELECT * FROM user)")
    fun isNotEmpty(): Flow<Boolean>
}