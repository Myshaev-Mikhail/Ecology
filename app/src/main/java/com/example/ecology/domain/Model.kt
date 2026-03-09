package com.example.ecology.domain

import com.example.ecology.data.local.UserRole

data class Report(
    val id: Int = 0,
    val userId: Int?,
    val district: String,
    val street: String,
    val house: String,
    val description: String,
    val photo: String?
)

data class User(
    val id: Int = 0,
    val role: UserRole,
    val nickname: String,
    val email: String,
    val password: String,
    val isSubscription: Boolean
)