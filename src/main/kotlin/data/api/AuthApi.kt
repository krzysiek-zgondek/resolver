package data.api

import data.api.dto.CredentialsDto
import data.api.dto.SessionDto
import data.model.Credentials
import io.ktor.client.*
import io.ktor.client.request.*

class AuthApi(private val httpClient: HttpClient) {
    suspend fun register(credentials: Credentials): SessionDto =
        httpClient.post {
            url { encodedPath = "api/register" }
            body = credentials.let(::CredentialsDto)
        }

}