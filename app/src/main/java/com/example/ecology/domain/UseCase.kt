package com.example.ecology.domain

import kotlinx.coroutines.flow.Flow

class SaveReportUseCase(
    private val repository: ReportRepository
) {
    suspend operator fun invoke(report: Report) {
        repository.saveReport(report)
    }

    fun itemsList(): Flow<List<Report>> {
        return repository.itemsList()
    }
}

class SaveUserUseCase(
    private val repository: UserRepository
) {
    suspend operator fun invoke(user: User) {
        repository.saveUser(user)
    }

    fun isUser(): Flow<Boolean> {
        return repository.isUser()
    }
}

class LoginUserUseCase(
    private val repository: UserRepository
) {
    suspend operator fun invoke(
        email: String,
        password: String
    ): Boolean {
        return repository.login(email, password)
    }
}