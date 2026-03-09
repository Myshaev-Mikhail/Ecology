package com.example.ecology.ui.screen.allreport.intents

import com.example.ecology.domain.Report

data class AllReportState(
    val reports: List<GroupedReport> = emptyList(),
    val isAuthUser: Boolean = false
)

data class GroupedReport(
    val district: String,
    val street: String,
    val house: String,
    val reports: List<Report>
) {
    val count: Int
        get() = reports.size

    val previewReport: Report
        get() = reports.first()
}