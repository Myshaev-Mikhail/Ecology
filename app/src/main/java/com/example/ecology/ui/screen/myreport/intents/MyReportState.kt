package com.example.ecology.ui.screen.myreport.intents

import com.example.ecology.domain.Report

data class MyReportState(
    val reports: List<Report> = emptyList(),
    val isAuthUser: Boolean = false
)