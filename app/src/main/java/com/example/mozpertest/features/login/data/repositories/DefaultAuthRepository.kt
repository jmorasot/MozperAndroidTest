package com.example.mozpertest.features.login.data.repositories

import com.example.mozpertest.features.login.domain.model.User
import com.example.mozpertest.features.login.domain.repositories.AuthRepository
import com.example.mozpertest.features.login.domain.repositories.AuthUserError

class DefaultAuthRepository : AuthRepository {

    override suspend fun authUser(user: String, password: String): User {
        return try {
            User(
                username = user,
                firstName = user.substring(0, user.indexOf('@')),
                lastName = "MozperTest"
            )
        } catch (exception: Exception) {
            throw AuthUserError()
        }
    }
}