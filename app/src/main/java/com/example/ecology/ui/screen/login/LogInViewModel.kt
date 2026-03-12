package com.example.ecology.ui.screen.login

import com.example.ecology.data.session.SessionManager
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
    private val loginUserUseCase: LoginUserUseCase,
    private val sessionManager: SessionManager
) : BaseViewModel() {
    private val uiStateFlow = MutableStateFlow(LogInState())
    val uiStateEmitter = uiStateFlow.asStateFlow()

    private val sideEffectFlow = MutableSharedFlow<LogInSideEffect>()
    val sideEffectEmitter = sideEffectFlow.asSharedFlow()

    fun handleUiAction(action: LogInAction) {
        when (action) {
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

            is LogInAction.NavigationNewReport -> {
                launchSafe {
                    val state = uiStateFlow.value
                    val user = loginUserUseCase(
                        state.email,
                        state.password
                    )

                    if (user != null) {
                        sessionManager.login(user.id)

                        sideEffectFlow.emit(
                            LogInSideEffect.ShowNavigationNewReport
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