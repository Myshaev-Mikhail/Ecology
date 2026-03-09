package com.example.ecology.domain

import kotlinx.coroutines.flow.Flow

interface ReportRepository {
    suspend fun saveReport(report: Report)
    fun observeAllReports(): Flow<List<Report>>

    fun observeUserReports(userId: Int): Flow<List<Report>>

    fun observeOtherReports(userId: Int): Flow<List<Report>>

    fun observeGuestReports(): Flow<List<Report>>
}

interface UserRepository {
    suspend fun saveUser(user: User): Int

    fun isUser(): Flow<Boolean>

    suspend fun login(email: String, password: String): User?
}