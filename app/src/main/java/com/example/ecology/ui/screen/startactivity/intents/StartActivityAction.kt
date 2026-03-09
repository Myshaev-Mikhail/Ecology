package com.example.ecology.ui.screen.startactivity.intents

sealed class StartActivityAction {
    data object NavigationNewReport: StartActivityAction()
    data object NavigationLogIn: StartActivityAction()
}