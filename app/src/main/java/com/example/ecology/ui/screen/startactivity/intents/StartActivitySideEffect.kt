package com.example.ecology.ui.screen.startactivity.intents

sealed class StartActivitySideEffect {
    data object ShowNewReport: StartActivitySideEffect()
    data object ShowMediaAccess : StartActivitySideEffect()
}