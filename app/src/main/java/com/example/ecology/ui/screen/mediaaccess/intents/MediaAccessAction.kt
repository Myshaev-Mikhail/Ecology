package com.example.ecology.ui.screen.mediaaccess.intents

sealed class MediaAccessAction {
    data object NavigationBack : MediaAccessAction()
    data class EmailChange(val value: String) : MediaAccessAction()
    data class PasswordChange(val value: String) : MediaAccessAction()
    data object TogglePasswordVisibility : MediaAccessAction()
    data object Subscription : MediaAccessAction()
    data object NavigationNewReport : MediaAccessAction()
}