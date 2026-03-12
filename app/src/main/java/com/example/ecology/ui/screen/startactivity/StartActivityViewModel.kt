package com.example.ecology.ui.screen.startactivity

import com.example.ecology.ui.screen.BaseViewModel
import com.example.ecology.ui.screen.startactivity.intents.StartActivityAction
import com.example.ecology.ui.screen.startactivity.intents.StartActivitySideEffect
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

class StartActivityViewModel : BaseViewModel() {
    private val sideEffectFlow = MutableSharedFlow<StartActivitySideEffect>()
    val sideEffectEmitter = sideEffectFlow.asSharedFlow()

    fun handleUiAction(action: StartActivityAction) {
        when (action) {
            is StartActivityAction.NavigationNewReport -> {
                launchSafe {
                    sideEffectFlow.emit(
                        StartActivitySideEffect.ShowNewReport
                    )
                }
            }

            is StartActivityAction.NavigationLogIn -> {
                launchSafe {
                    sideEffectFlow.emit(
                        StartActivitySideEffect.ShowLogIn
                    )
                }
            }
        }
    }
}