package com.example.mozpertest.features.login.domain.usecases

import com.example.mozpertest.features.core.domain.models.ResultWrapper
import com.example.mozpertest.features.core.domain.usecases.UseCase
import com.example.mozpertest.features.login.domain.model.User
import com.example.mozpertest.features.login.domain.repositories.SessionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RetrieveSessionUseCase(
    private val sessionRepository: SessionRepository
) : UseCase<Any?, User> {

    override suspend operator fun invoke(params: Any?): Flow<ResultWrapper<User>> = flow {
        try {
            emit(ResultWrapper.Success(sessionRepository.retrieveSession()))
        } catch (exception: Exception) {
            emit(ResultWrapper.Failure(exception.cause ?: Throwable()))
        }
    }
}
