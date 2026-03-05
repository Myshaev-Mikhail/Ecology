package com.example.ecology.ui.screen.myreport.intents

sealed class MyReportSideEffect {
    data object ShowNavigationNewReport : MyReportSideEffect()
    data object ShowNavigationAllReport : MyReportSideEffect()
}