package com.example.ecology.domain

import kotlinx.coroutines.flow.Flow

class SaveReportUseCase(
    private val repository: ReportRepository
) {
    suspend operator fun invoke(report: Report) {
        repository.saveReport(report)
    }

    // Все карточки
    fun observeAllReports() =
        repository.observeAllReports()

    // Карточки конкретного пользователя (для экрана "Мои")
    fun observeUserReports(userId: Int) =
        repository.observeUserReports(userId)

    // Чужие карточки (для медийных пользователей)
    fun observeOtherReports(userId: Int) =
        repository.observeOtherReports(userId)

    // Карточки гостей (незарегистрированных пользователей)
    fun observeGuestReports() =
        repository.observeGuestReports()
}

class SaveUserUseCase(
    private val repository: UserRepository
) {
    suspend operator fun invoke(user: User): Int {
        return repository.saveUser(user)
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
    ): User? {
        return repository.login(email, password)
    }
}