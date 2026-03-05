package com.example.ecology.ui.screen.allreport.intents

sealed class AllReportAction {
    data object NavigationNewReport : AllReportAction()
    data object NavigationMyReport : AllReportAction()
}