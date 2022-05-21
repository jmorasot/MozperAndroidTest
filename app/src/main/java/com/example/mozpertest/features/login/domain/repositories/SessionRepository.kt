package com.example.mozpertest.features.login.domain.repositories

import com.example.mozpertest.features.login.domain.model.User

class InvalidSessionException : Exception("No session found")

interface SessionRepository {
    suspend fun saveSession(user: User)

    @Throws(InvalidSessionException::class)
    suspend fun retrieveSession(): User
}
