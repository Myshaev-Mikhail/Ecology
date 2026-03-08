package com.example.ecology.ui.screen.signup.intents

data class SignUpState(
    val nickname: String = "",
    val email: String = "",
    val password: String = "",
    val passwordVisible: Boolean = false,
    val isPopup: Boolean = false,
    val isSubscription: Boolean = false
)
