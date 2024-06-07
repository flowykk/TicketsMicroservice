package hse.kpo.authorizationservice.repository

import hse.kpo.authorizationservice.models.Session
import hse.kpo.authorizationservice.models.User
import org.springframework.data.repository.CrudRepository

interface SessionRepository : CrudRepository<Session, Int> {
    fun findByToken(token: String) : Session?
}