package com.example.ecology.domain

import kotlinx.coroutines.flow.Flow

interface ReportRepository {
    suspend fun saveReport(report: Report)
    fun itemsList(): Flow<List<Report>>
}

interface UserRepository {
    suspend fun saveUser(user: User)
    fun isUser(): Flow<Boolean>

    suspend fun login(email: String, password: String): Boolean
}