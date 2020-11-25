package data.api.dto

import data.model.Credentials
import kotlinx.serialization.Serializable

@Serializable
data class CredentialsDto(
    val email: String,
    val password: String,
) {
    constructor(credentials: Credentials) : this(
        email = credentials.email,
        password = credentials.password
    )

    inline val credentials
        get() = Credentials(
            email = email,
            password = password,
        )
}