package com.example.ecology.ui.screen.mediaaccess.intents

sealed class MediaAccessSideEffect {
    data object ShowNavigationBack : MediaAccessSideEffect()
    data object ShowSubscription : MediaAccessSideEffect()
    data object ShowNewReport : MediaAccessSideEffect()
}