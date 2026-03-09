package com.example.ecology.ui.screen.login.intents

sealed class LogInSideEffect {
    data object ShowNavigationBack : LogInSideEffect()
    data object ShowNavigationNewReport : LogInSideEffect()
    data object ShowNavigationSignUp : LogInSideEffect()
    data object ShowInvalidCredentials : LogInSideEffect()
}