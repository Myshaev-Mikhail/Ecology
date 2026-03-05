package com.example.ecology.ui.screen.allreport.intents

import com.example.ecology.domain.Report

data class AllReportState(
    val reports: List<Report> = emptyList(),
    val isAuthUser: Boolean = false
)
