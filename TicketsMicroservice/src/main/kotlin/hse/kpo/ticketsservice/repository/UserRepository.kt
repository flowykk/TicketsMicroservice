package hse.kpo.ticketsservice.repository

import hse.kpo.ticketsservice.models.User
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Int> {
    fun findByEmail(email: String): User?
    fun getById(id: Int): User?
}