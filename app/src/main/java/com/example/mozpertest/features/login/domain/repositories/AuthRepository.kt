package com.example.mozpertest.features.login.domain.repositories

import com.example.mozpertest.features.login.domain.model.User

class AuthUserError : Throwable("The provided credentials are not valid")

interface AuthRepository {
    @Throws(AuthUserError::class)
    suspend fun authUser(user: String, password: String): User
}
