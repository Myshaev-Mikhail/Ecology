package com.example.ecology.ui.screen.login.intents

sealed class LogInAction {
    data object NavigationBack : LogInAction()
    data class EmailChange(val value: String) : LogInAction()
    data class PasswordChange(val value: String) : LogInAction()
    data object TogglePasswordVisibility : LogInAction()
    data object NavigationMediaAccess : LogInAction()
    data object NavigationSignUp : LogInAction()
}