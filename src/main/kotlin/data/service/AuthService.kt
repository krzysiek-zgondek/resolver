package data.service

import data.model.Credentials
import data.model.Session
import core.Resource

interface AuthService {
    suspend fun register(credentials: Credentials): Resource<Session, Throwable>
}