package com.example.ecology.ui.screen.newreport

import androidx.lifecycle.viewModelScope
import com.example.ecology.domain.Report
import com.example.ecology.domain.SaveReportUseCase
import com.example.ecology.domain.SaveUserUseCase
import com.example.ecology.ui.screen.BaseViewModel
import com.example.ecology.ui.screen.newreport.intents.NewReportAction
import com.example.ecology.ui.screen.newreport.intents.NewReportSideEffect
import com.example.ecology.ui.screen.newreport.intents.NewReportState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NewReportViewModel(
    private val saveReportUseCase: SaveReportUseCase,
    private val saveUserUseCase: SaveUserUseCase
): BaseViewModel() {
    private val uiStateFlow = MutableStateFlow(NewReportState())
    val uiStateEmitter = uiStateFlow.asStateFlow()

    private val sideEffectFlow = MutableSharedFlow<NewReportSideEffect>()
    val sideEffectEmitter = sideEffectFlow.asSharedFlow()

    val reports = saveReportUseCase.itemsList()

    init {
        viewModelScope.launch {
            saveUserUseCase.isUser().collect { user ->
                uiStateFlow.value = uiStateFlow.value.copy(
                    isAuthUser = user
                )
            }
        }
    }
    fun handleUiAction(action: NewReportAction) {
        when (action) {
            is NewReportAction.NavigationMyReport -> {
                launchSafe {
                    sideEffectFlow.emit(
                        NewReportSideEffect.ShowNavigationMyReport
                    )
                }
            }

            is NewReportAction.NavigationAllReport -> {
                launchSafe {
                    sideEffectFlow.emit(
                        NewReportSideEffect.ShowNavigationAllReport
                    )
                }
            }

            is NewReportAction.DistrictChanged -> {
                uiStateFlow.value = uiStateFlow.value.copy(
                    district = action.value
                )
            }

            is NewReportAction.StreetChanged -> {
                uiStateFlow.value = uiStateFlow.value.copy(
                    street = action.value
                )
            }

            is NewReportAction.HouseChanged -> {
                uiStateFlow.value = uiStateFlow.value.copy(
                    house = action.value
                )
            }

            is NewReportAction.DescriptionChanged -> {
                uiStateFlow.value = uiStateFlow.value.copy(
                    description = action.value
                )
            }

            is NewReportAction.PhotoSelected -> {
                uiStateFlow.value = uiStateFlow.value.copy(
                    photo = action.uri
                )
            }


            is NewReportAction.SubmitClick -> {
                launchSafe {
                    val state = uiStateFlow.value
                    saveReportUseCase(
                        Report(
                            district = state.district,
                            street = state.street,
                            house = state.house,
                            description = state.description,
                            photo = state.photo?.toString()
                        )
                    )

                    // очищаем форму после сохранения
                    uiStateFlow.value = NewReportState()
                }
            }

            else -> {}
        }
    }
}