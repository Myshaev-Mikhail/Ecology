package com.example.ecology.ui.screen.newreport.intents

sealed class NewReportSideEffect {
    data object ShowNavigationMyReport: NewReportSideEffect()
    data object ShowNavigationAllReport: NewReportSideEffect()
}