package com.example.ecology.ui.screen.newreport.intents

import android.net.Uri

sealed class NewReportAction {
    data object NavigationMyReport: NewReportAction()
    data object NavigationAllReport: NewReportAction()
    data class DistrictChanged(val value: String) : NewReportAction()
    data class StreetChanged(val value: String) : NewReportAction()
    data class HouseChanged(val value: String) : NewReportAction()
    data class DescriptionChanged(val value: String) : NewReportAction()
    data class PhotoSelected(val uri: Uri) : NewReportAction()
    data object SubmitClick : NewReportAction()
}