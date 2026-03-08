package com.example.ecology.ui.screen.mediaaccess

import com.example.ecology.domain.SaveUserUseCase
import com.example.ecology.domain.User
import com.example.ecology.ui.screen.BaseViewModel
import com.example.ecology.ui.screen.mediaaccess.intents.MediaAccessAction
import com.example.ecology.ui.screen.mediaaccess.intents.MediaAccessSideEffect
import com.example.ecology.ui.screen.mediaaccess.intents.MediaAccessState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

class MediaAccessViewModel(
    private val saveUserUseCase: SaveUserUseCase
): BaseViewModel() {
    private val uiStateFlow = MutableStateFlow(MediaAccessState())
    val uiStateEmitter = uiStateFlow.asStateFlow()

    private val sideEffectFlow = MutableSharedFlow<MediaAccessSideEffect>()
    val sideEffectEmitter = sideEffectFlow.asSharedFlow()

    fun handleUiAction(action: MediaAccessAction) {
        when(action) {
            is MediaAccessAction.NavigationBack -> {
                launchSafe {
                    sideEffectFlow.emit(
                        MediaAccessSideEffect.ShowNavigationBack
                    )
                }
            }
            is MediaAccessAction.Subscription -> {
                launchSafe {

                }
            }
            is MediaAccessAction.NavigationNewReport -> {
                launchSafe {
                    sideEffectFlow.emit(
                        MediaAccessSideEffect.ShowNewReport
                    )
                }
            }
        }
    }
}