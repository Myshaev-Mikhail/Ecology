package com.example.ecology.ui.screen.newreport.intents

import android.net.Uri

data class NewReportState(
    val district: String = "",
    val street: String = "",
    val house: String = "",
    val description: String = "",
    val photo: Uri? = null,
    val isAuthUser: Boolean = false
)
