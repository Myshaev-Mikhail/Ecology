package com.example.ecology.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

open class BaseViewModel: ViewModel() {
    /**
     * Mutex = только одна coroutine одновременно
     */
    private val mutex = Mutex()

    /**
     * Error Flow
     */
    private val _errorFlow = MutableSharedFlow<String>()
    val errorFlow: SharedFlow<String> = _errorFlow

    /**
     * Exception handler
     */
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        handleError(throwable)
    }

    /**
     * Single-thread safe launch
     * Новая задача ждёт завершения предыдущей
     */
    protected fun launchSafe(
        block: suspend () -> Unit
    ) {
        viewModelScope.launch(exceptionHandler) {
            mutex.withLock {
                try {
                    block()
                }
                catch (e: CancellationException) {
                    throw e
                }
                catch (e: Exception) {
                    handleError(e)
                }
            }
        }
    }

    /**
     * Error handler
     */
    protected open fun handleError(
        throwable: Throwable
    ) {
        viewModelScope.launch {
            _errorFlow.emit(
                throwable.message ?: "Unknown error"
            )
        }
    }

    /**
     * Manual error
     */
    protected fun sendError(
        message: String
    ) {
        viewModelScope.launch {
            _errorFlow.emit(message)
        }
    }
}