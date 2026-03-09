package com.example.ecology.ui.screen.signup.intents

sealed class SignUpAction {
    data object NavigationBack : SignUpAction()
    data class NicknameChange(val value: String) : SignUpAction()
    data class EmailChange(val value: String) : SignUpAction()
    data class PasswordChange(val value: String) : SignUpAction()
    data object TogglePasswordVisibility : SignUpAction()
    data object OnPopup : SignUpAction()
    data object NavigationLogIn : SignUpAction()
    data object Subscription : SignUpAction()
    data object ClosePopup : SignUpAction()
}