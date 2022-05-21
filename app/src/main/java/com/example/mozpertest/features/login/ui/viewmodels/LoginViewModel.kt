package com.example.mozpertest.features.login.ui.viewmodels

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mozpertest.features.core.domain.models.ResultWrapper
import com.example.mozpertest.features.login.domain.repositories.AuthUserError
import com.example.mozpertest.features.login.domain.usecases.LoginUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val saveSessionUseCase: SaveSessionUseCase,
    private val retrieveSessionUseCase: RetrieveSessionUseCase
) : ViewModel() {

    val isLoggedIn = MutableSharedFlow<Boolean>()
    val isLoading = MutableSharedFlow<Boolean>()

    fun authenticate(
        user: String,
        password: String
    ) {
        if (Patterns.EMAIL_ADDRESS.matcher(user).matches() && password.isNotEmpty()) {
            viewModelScope.launch {
                isLoading.emit(true)
                loginUseCase(
                    LoginUseCase.LoginParams(user, password)
                ).collect { result ->
                    isLoading.emit(false)
                    when (result) {
                        is ResultWrapper.Success -> isLoggedIn.emit(true)
                        is ResultWrapper.Failure -> {
                            isLoggedIn.emit(false)
                            processFailure(result.error)
                        }
                    }
                }
            }
        } else {
            // throw error
        }
    }

    private fun processFailure(error: Throwable) {
        if (error is AuthUserError) {

            return
        }
    }
}
