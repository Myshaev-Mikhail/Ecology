package com.example.ecology.ui.screen.myreport

import androidx.lifecycle.viewModelScope
import com.example.ecology.domain.SaveReportUseCase
import com.example.ecology.domain.SaveUserUseCase
import com.example.ecology.ui.screen.BaseViewModel
import com.example.ecology.ui.screen.myreport.intents.MyReportAction
import com.example.ecology.ui.screen.myreport.intents.MyReportSideEffect
import com.example.ecology.ui.screen.myreport.intents.MyReportState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MyReportViewModel(
    private val saveReportUseCase: SaveReportUseCase,
    private val saveUserUseCase: SaveUserUseCase
): BaseViewModel() {
    private val uiStateFlow = MutableStateFlow(MyReportState())
    val uiStateEmitter = uiStateFlow.asStateFlow()

    private val sideEffectFlow = MutableSharedFlow<MyReportSideEffect>()
    val sideEffectEmitter = sideEffectFlow.asSharedFlow()

    init {
        observeReports()
    }

    private fun observeReports() {
        viewModelScope.launch {
            saveReportUseCase.itemsList().collect { list ->
                uiStateFlow.value = uiStateFlow.value.copy(
                    reports = list
                )
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

    fun handleUiAction(action: MyReportAction) {
        when (action) {
            is MyReportAction.NavigationNewReport -> {
                launchSafe {
                    sideEffectFlow.emit(
                        MyReportSideEffect.ShowNavigationNewReport
                    )
                }
            }
            is MyReportAction.NavigationAllReport -> {
                launchSafe {
                    sideEffectFlow.emit(
                        MyReportSideEffect.ShowNavigationAllReport
                    )
                }
            }
        }
    }
}