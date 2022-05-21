package com.example.mozpertest.features.login.domain.usecases

import com.example.mozpertest.features.core.domain.models.ResultWrapper
import com.example.mozpertest.features.core.domain.usecases.UseCase
import com.example.mozpertest.features.login.domain.model.User
import com.example.mozpertest.features.login.domain.repositories.AuthRepository
import kotlinx.coroutines.flow.Flow

class LoginUseCase(
    private val authRepository: AuthRepository
) : UseCase<LoginUseCase.LoginParams, User> {

    data class LoginParams(
        val user: String,
        val password: String
    )

    override suspend operator fun invoke(params: LoginParams): Flow<ResultWrapper<User>> {
        return authRepository.authUser(params.user, params.password)
    }
}
