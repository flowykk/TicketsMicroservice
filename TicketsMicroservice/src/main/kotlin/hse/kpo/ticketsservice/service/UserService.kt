package hse.kpo.ticketsservice.service

import hse.kpo.ticketsservice.dtos.RegisterDTO
import hse.kpo.ticketsservice.repository.SessionRepository
import hse.kpo.ticketsservice.repository.UserRepository
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import kotlin.io.encoding.ExperimentalEncodingApi

@Service
@ExperimentalEncodingApi
@RequestMapping("api")
class UserService(
    private val userRepository: UserRepository,
    private val sessionRepository: SessionRepository,
) {
    @Transactional
    fun registerUser() : HttpEntity<String> {
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully")
    }
}