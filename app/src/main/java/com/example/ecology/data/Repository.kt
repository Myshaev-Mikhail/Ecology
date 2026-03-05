package com.example.ecology.data

import com.example.ecology.data.local.ReportDao
import com.example.ecology.data.local.UserDao
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

    override fun itemsList(): Flow<List<Report>> {
        return dao.observeReports()
            .map { list ->
                list.map { entity ->
                    Report(
                        district = entity.district,
                        street = entity.street,
                        house = entity.house,
                        description = entity.description,
                        photo = entity.photo
                    )
                }
            }
    }
}

class UserRepositoryImpl(
    private val dao: UserDao
) : UserRepository {
    override suspend fun saveUser(user: User) {
        dao.insert(user.toEntity())
    }

    override fun isUser(): Flow<Boolean> {
        return dao.isNotEmpty()
    }
}