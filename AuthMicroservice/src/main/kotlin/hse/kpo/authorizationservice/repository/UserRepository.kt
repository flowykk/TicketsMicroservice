package hse.kpo.authorizationservice.repository

import hse.kpo.authorizationservice.models.User
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Int> {
    fun findByEmail(email: String): User?
    fun getById(id: Int?): User?
}