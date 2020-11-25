package data.api.dto

import data.model.Session
import kotlinx.serialization.Serializable

@Serializable
data class SessionDto(
    val token: String,
    val id: Long,
) {
    constructor(session: Session) : this(
        token = session.token,
        id = session.id
    )

    val session
        get() = Session(
            token = token,
            id = id,
        )
}