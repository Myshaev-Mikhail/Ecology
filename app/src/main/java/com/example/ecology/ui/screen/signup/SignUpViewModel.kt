package com.example.ecology.ui.screen.signup

import com.example.ecology.domain.SaveUserUseCase
import com.example.ecology.domain.User
import com.example.ecology.ui.screen.BaseViewModel
import com.example.ecology.ui.screen.signup.intents.SignUpAction
import com.example.ecology.ui.screen.signup.intents.SignUpSideEffect
import com.example.ecology.ui.screen.signup.intents.SignUpState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

class SignUpViewModel(
    private val saveUserUseCase: SaveUserUseCase
) : BaseViewModel() {
    private val uiStateFlow = MutableStateFlow(SignUpState())
    val uiStateEmitter = uiStateFlow.asStateFlow()

    private val sideEffectFlow = MutableSharedFlow<SignUpSideEffect>()
    val sideEffectEmitter = sideEffectFlow.asSharedFlow()

    fun handleUiAction(action: SignUpAction) {
        when(action) {
            is SignUpAction.NavigationBack -> {
                launchSafe {
                    sideEffectFlow.emit(
                        SignUpSideEffect.ShowNavigationBack
                    )
                }
            }
            is SignUpAction.NicknameChange -> {
                launchSafe {
                    uiStateFlow.value = uiStateFlow.value.copy(
                        nickname = action.value
                    )
                }
            }
            is SignUpAction.EmailChange -> {
                launchSafe {
                    uiStateFlow.value = uiStateFlow.value.copy(
                        email = action.value
                    )
                }
            }
            is SignUpAction.PasswordChange -> {
                launchSafe {
                    uiStateFlow.value = uiStateFlow.value.copy(
                        password = action.value
                    )
                }
            }
            is SignUpAction.TogglePasswordVisibility -> {
                launchSafe {
                    uiStateFlow.value = uiStateFlow.value.copy(
                        passwordVisible = !uiStateFlow.value.passwordVisible
                    )
                }
            }
            is SignUpAction.OnPopup -> {
                launchSafe {
                    uiStateFlow.value = uiStateFlow.value.copy(isPopup = true)
                }
            }
            is SignUpAction.ClosePopup -> {
                launchSafe {
                    uiStateFlow.value = uiStateFlow.value.copy(isPopup = false)
                }
            }
            is SignUpAction.NavigationLogIn -> {
                launchSafe {
                    sideEffectFlow.emit(
                        SignUpSideEffect.ShowNavigationLogIn
                    )
                }
            }
            is SignUpAction.Subscription -> {
                launchSafe {
                    // Сохраняем пользователя с подпиской
                    val currentState = uiStateFlow.value.copy(isSubscription = true)
                    saveUserUseCase(
                        User(
                            nickname = currentState.nickname,
                            email = currentState.email,
                            password = currentState.password,
                            isSubscription = currentState.isSubscription
                        )
                    )

                    // Переходим на следующий экран
                    sideEffectFlow.emit(SignUpSideEffect.ShowNavigationNewReport)

                    // Только после навигации закрываем popup (если нужно)
                    uiStateFlow.value = currentState.copy(isPopup = false)
                }
            }
        }
    }
}