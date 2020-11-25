package data.service

import data.model.Credentials
import core.Owner
import core.safeResource
import data.api.AuthApi
import kotlinx.coroutines.coroutineScope

class AuthServiceImpl(private val authApi: AuthApi) : AuthService {
    override suspend fun register(credentials: Credentials) = safeResource {
        coroutineScope {
            println(coroutineContext[Owner])
        }
        validate(credentials)
        authApi.register(credentials).session
    }

    private fun validate(credentials: Credentials) {
        val parts = credentials.email.split("@")
        if (parts.size < 2 || parts.any(String::isEmpty)) {
            throw IllegalArgumentException("email is incorrect")
        }

        if (credentials.password.length < 6) {
            throw IllegalArgumentException("password is too short")
        }
    }
}