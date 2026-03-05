package com.example.ecology.ui.screen.myreport.intents

sealed class MyReportAction {
    data object NavigationNewReport : MyReportAction()
    data object NavigationAllReport : MyReportAction()
}