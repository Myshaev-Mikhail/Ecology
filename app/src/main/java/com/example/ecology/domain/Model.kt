package com.example.ecology.domain

data class Report(
    val district: String,
    val street: String,
    val house: String,
    val description: String,
    val photo: String?
)

data class User(
    val email: String,
    val password: String
)