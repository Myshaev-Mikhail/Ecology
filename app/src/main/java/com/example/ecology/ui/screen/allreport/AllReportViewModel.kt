package com.example.ecology.ui.screen.allreport

import androidx.lifecycle.viewModelScope
import com.example.ecology.data.session.SessionManager
import com.example.ecology.domain.SaveReportUseCase
import com.example.ecology.domain.SaveUserUseCase
import com.example.ecology.ui.screen.BaseViewModel
import com.example.ecology.ui.screen.allreport.intents.AllReportAction
import com.example.ecology.ui.screen.allreport.intents.AllReportSideEffect
import com.example.ecology.ui.screen.allreport.intents.AllReportState
import com.example.ecology.ui.screen.allreport.intents.GroupedReport
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AllReportViewModel(
    private val saveReportUseCase: SaveReportUseCase,
    private val saveUserUseCase: SaveUserUseCase,
    private val sessionManager: SessionManager
): BaseViewModel() {
    private val uiStateFlow = MutableStateFlow(AllReportState())
    val uiStateEmitter = uiStateFlow.asStateFlow()

    private val sideEffectFlow = MutableSharedFlow<AllReportSideEffect>()
    val sideEffectEmitter = sideEffectFlow.asSharedFlow()

    init {
        observe()
    }
    private fun observe() {
        val userId = sessionManager.getUserId()
        viewModelScope.launch {
            if (userId != null) {
                saveReportUseCase.observeAllReports()
                    .collect { list ->
                        val grouped = list
                            .groupBy { Triple(it.district, it.street, it.house) }
                            .map { (key, reports) ->
                                GroupedReport(
                                    district = key.first,
                                    street = key.second,
                                    house = key.third,
                                    reports = reports
                                )
                            }

                        uiStateFlow.value = uiStateFlow.value.copy(
                            reports = grouped
                        )
                    }
            }
        }
        viewModelScope.launch {
            saveUserUseCase.isUser().collect { user ->
                uiStateFlow.value = uiStateFlow.value.copy(
                    isAuthUser = user
                )
            }
        }
    }

    fun handleUiAction(action: AllReportAction) {
        when(action) {
            is AllReportAction.NavigationNewReport -> {
                launchSafe {
                    sideEffectFlow.emit(
                        AllReportSideEffect.ShowNavigationNewReport
                    )
                }
            }
            is AllReportAction.NavigationMyReport -> {
                launchSafe {
                    sideEffectFlow.emit(
                        AllReportSideEffect.ShowNavigationMyReport
                    )
                }
            }
        }
    }
}