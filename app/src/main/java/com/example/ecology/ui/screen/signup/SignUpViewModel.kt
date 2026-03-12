package com.example.ecology.ui.screen.signup

import android.util.Patterns
import com.example.ecology.data.local.UserRole
import com.example.ecology.data.session.SessionManager
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
    private val saveUserUseCase: SaveUserUseCase,
    private val sessionManager: SessionManager
) : BaseViewModel() {
    private val uiStateFlow = MutableStateFlow(SignUpState())
    val uiStateEmitter = uiStateFlow.asStateFlow()

    private val sideEffectFlow = MutableSharedFlow<SignUpSideEffect>()
    val sideEffectEmitter = sideEffectFlow.asSharedFlow()

    fun handleUiAction(action: SignUpAction) {
        when (action) {
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
                    val state = uiStateFlow.value

                    if (!isValidEmail(state.email)) {
                        sideEffectFlow.emit(
                            SignUpSideEffect.ShowToast("Введите корректный email")
                        )
                        return@launchSafe
                    }

                    uiStateFlow.value = state.copy(isPopup = true)
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
                    val currentState = uiStateFlow.value.copy(isSubscription = true)
                    val user = User(
                        id = 0,
                        role = UserRole.MEDIA,
                        nickname = currentState.nickname,
                        email = currentState.email,
                        password = currentState.password,
                        isSubscription = true
                    )
                    val userId = saveUserUseCase(user)
                    sessionManager.login(userId)

                    sideEffectFlow.emit(SignUpSideEffect.ShowNavigationNewReport)

                    uiStateFlow.value = currentState.copy(isPopup = false)
                }
            }
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}