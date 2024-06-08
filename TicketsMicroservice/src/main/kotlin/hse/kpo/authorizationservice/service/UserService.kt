package hse.kpo.authorizationservice.service

import hse.kpo.authorizationservice.dtos.LoginDTO
import hse.kpo.authorizationservice.dtos.RegisterDTO
import hse.kpo.authorizationservice.models.Session
import hse.kpo.authorizationservice.models.User
import hse.kpo.authorizationservice.repository.SessionRepository
import hse.kpo.authorizationservice.repository.UserRepository
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import java.nio.charset.StandardCharsets
import java.security.Key
import java.sql.Timestamp
import java.time.Instant
import java.util.*
import kotlin.io.encoding.ExperimentalEncodingApi

@Service
@ExperimentalEncodingApi
@RequestMapping("api")
class UserService(
    private val userRepository: UserRepository,
    private val sessionRepository: SessionRepository,
) {

}