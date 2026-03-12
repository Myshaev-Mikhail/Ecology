package com.example.ecology.ui.screen.signup.intents

sealed class SignUpSideEffect {
    data object ShowNavigationBack : SignUpSideEffect()
    data object ShowNavigationNewReport : SignUpSideEffect()
    data object ShowNavigationLogIn : SignUpSideEffect()
    data class ShowToast(val message: String) : SignUpSideEffect()
}