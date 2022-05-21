package com.example.mozpertest.features.login.di.modules

import com.example.mozpertest.features.login.data.repositories.DefaultAuthRepository
import com.example.mozpertest.features.login.data.repositories.DefaultSessionRepository
import com.example.mozpertest.features.login.domain.repositories.AuthRepository
import com.example.mozpertest.features.login.domain.repositories.SessionRepository
import com.example.mozpertest.features.login.domain.usecases.LoginUseCase
import com.example.mozpertest.features.login.domain.usecases.RetrieveSessionUseCase
import com.example.mozpertest.features.login.domain.usecases.SaveSessionUseCase
import com.example.mozpertest.features.login.ui.viewmodels.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object LoginModule {
    val module by lazy {
        module {
            viewModel {
                LoginViewModel(
                    loginUseCase = get(),
                    saveSessionUseCase = get(),
                    retrieveSessionUseCase = get()
                )
            }

            factory {
                LoginUseCase(
                    authRepository = get()
                )
            }

            factory {
                SaveSessionUseCase(
                    sessionRepository = get()
                )
            }

            factory {
                RetrieveSessionUseCase(
                    sessionRepository = get()
                )
            }

            factory<AuthRepository> {
                DefaultAuthRepository()
            }

            factory<SessionRepository> {
                DefaultSessionRepository()
            }
        }
    }
}
