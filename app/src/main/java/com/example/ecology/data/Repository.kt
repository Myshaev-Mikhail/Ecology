package com.example.ecology.data

import com.example.ecology.data.local.ReportDao
import com.example.ecology.data.local.UserDao
import com.example.ecology.data.local.toDomain
import com.example.ecology.data.local.toEntity
import com.example.ecology.domain.Report
import com.example.ecology.domain.ReportRepository
import com.example.ecology.domain.User
import com.example.ecology.domain.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ReportRepositoryImpl(
    private val dao: ReportDao
) : ReportRepository {
    override suspend fun saveReport(report: Report) {
        dao.insert(report.toEntity())
    }

    override fun observeAllReports(): Flow<List<Report>> {
        return dao.observeAllReports()
            .map { list -> list.map { it.toDomain() } }
    }

    override fun observeUserReports(userId: Int): Flow<List<Report>> {
        return dao.observeUserReports(userId)
            .map { list -> list.map { it.toDomain() } }
    }

    override fun observeOtherReports(userId: Int): Flow<List<Report>> {
        return dao.observeOtherReports(userId)
            .map { list -> list.map { it.toDomain() } }
    }

    override fun observeGuestReports(): Flow<List<Report>> {
        return dao.observeGuestReports()
            .map { list -> list.map { it.toDomain() } }
    }
}

class UserRepositoryImpl(
    private val dao: UserDao
) : UserRepository {
    override suspend fun saveUser(user: User): Int {
        return dao.insert(user.toEntity()).toInt()
    }

    override fun isUser(): Flow<Boolean> {
        return dao.isNotEmpty()
    }

    override suspend fun login(email: String, password: String): User? {
        return dao.login(email, password)?.toDomain()
    }
}