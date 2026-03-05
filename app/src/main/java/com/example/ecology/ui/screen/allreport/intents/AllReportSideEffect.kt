package com.example.ecology.ui.screen.allreport.intents

sealed class AllReportSideEffect {
    data object ShowNavigationNewReport : AllReportSideEffect()
    data object ShowNavigationMyReport : AllReportSideEffect()
}