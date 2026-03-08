package com.example.ecology.domain

import android.provider.ContactsContract

data class Report(
    val district: String,
    val street: String,
    val house: String,
    val description: String,
    val photo: String?
)

data class User(
    val nickname: String,
    val email: String,
    val password: String,
    val isSubscription: Boolean
)