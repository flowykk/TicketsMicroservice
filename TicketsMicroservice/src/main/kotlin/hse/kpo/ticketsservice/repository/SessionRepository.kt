package hse.kpo.ticketsservice.repository

import hse.kpo.ticketsservice.models.Session
import org.springframework.data.repository.CrudRepository

interface SessionRepository : CrudRepository<Session, Int> {
}