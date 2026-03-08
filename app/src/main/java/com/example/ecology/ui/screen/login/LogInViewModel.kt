package com.example.ecology.ui.screen.login

import com.example.ecology.domain.LoginUserUseCase
import com.example.ecology.ui.screen.BaseViewModel
import com.example.ecology.ui.screen.login.intents.LogInAction
import com.example.ecology.ui.screen.login.intents.LogInSideEffect
import com.example.ecology.ui.screen.login.intents.LogInState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

class LogInViewModel(
    private val loginUserUseCase: LoginUserUseCase
) : BaseViewModel() {
    private val uiStateFlow = MutableStateFlow(LogInState())
    val uiStateEmitter = uiStateFlow.asStateFlow()

    private val sideEffectFlow = MutableSharedFlow<LogInSideEffect>()
    val sideEffectEmitter = sideEffectFlow.asSharedFlow()

    fun handleUiAction(action: LogInAction) {
        when(action) {
            is LogInAction.NavigationBack -> {
                launchSafe {
                    sideEffectFlow.emit(
                        LogInSideEffect.ShowNavigationBack
                    )
                }
            }
            is LogInAction.EmailChange -> {
                launchSafe {
                    uiStateFlow.value = uiStateFlow.value.copy(
                        email = action.value
                    )
                }
            }
            is LogInAction.PasswordChange -> {
                launchSafe {
                    uiStateFlow.value = uiStateFlow.value.copy(
                        password = action.value
                    )
                }
            }
            is LogInAction.TogglePasswordVisibility -> {
                launchSafe {
                    uiStateFlow.value = uiStateFlow.value.copy(
                        passwordVisible = !uiStateFlow.value.passwordVisible
                    )
                }
            }
            is LogInAction.NavigationMediaAccess -> {
                launchSafe {
                    val state = uiStateFlow.value
                    val success = loginUserUseCase(
                        state.email,
                        state.password
                    )

                    if (success) {
                        sideEffectFlow.emit(
                            LogInSideEffect.ShowNavigationMediaAccess
                        )
                    } else {
                        sideEffectFlow.emit(
                            LogInSideEffect.ShowInvalidCredentials
                        )
                    }
                }
            }
            is LogInAction.NavigationSignUp -> {
                launchSafe {
                    sideEffectFlow.emit(
                        LogInSideEffect.ShowNavigationSignUp
                    )
                }
            }
        }
    }
}