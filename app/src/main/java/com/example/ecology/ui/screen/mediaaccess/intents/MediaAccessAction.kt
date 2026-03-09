package com.example.ecology.ui.screen.mediaaccess.intents

sealed class MediaAccessAction {
    data object NavigationBack : MediaAccessAction()
    data object Subscription : MediaAccessAction()
    data object NavigationNewReport : MediaAccessAction()
}