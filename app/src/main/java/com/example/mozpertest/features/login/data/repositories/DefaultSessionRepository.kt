package com.example.mozpertest.features.login.data.repositories

import com.example.mozpertest.features.login.domain.model.User
import com.example.mozpertest.features.login.domain.repositories.SessionRepository

class DefaultSessionRepository : SessionRepository {
    override suspend fun saveSession(user: User) {
        // store session into room
    }

    override suspend fun retrieveSession(): User {
        // restore user from room
        return User("", "", "")
    }
}
