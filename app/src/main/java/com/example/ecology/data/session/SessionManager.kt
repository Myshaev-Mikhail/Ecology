package com.example.ecology.data.session

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SessionManager {

    private val currentUserIdFlow = MutableStateFlow<Int?>(null)

    val currentUserId = currentUserIdFlow.asStateFlow()

    fun login(userId: Int) {
        currentUserIdFlow.value = userId
    }

    fun logout() {
        currentUserIdFlow.value = null
    }

    fun getUserId(): Int? {
        return currentUserIdFlow.value
    }
}