package com.example.mozpertest.features.core.domain.usecases

import com.example.mozpertest.features.core.domain.models.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface UseCase<Params, Result> {
    suspend operator fun invoke(
        params: Params
    ): Flow<ResultWrapper<Result>>
}
