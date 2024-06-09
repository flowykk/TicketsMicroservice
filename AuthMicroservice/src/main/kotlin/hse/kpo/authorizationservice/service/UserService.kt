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
import java.sql.Timestamp
import java.time.Instant
import kotlin.io.encoding.ExperimentalEncodingApi

@Service
@ExperimentalEncodingApi
class UserService(
    private val passwordEncoder: PasswordEncoder,
    private val userRepository: UserRepository,
    private val sessionRepository: SessionRepository,
    private val validationService: ValidationService
) {
    private val secret: String = "eyJzdWIiOiJFUzI1NmluT1RBIiwibmFtZSI6IkpvaG4gRG9lIn0"

    @Transactional
    fun registerUser(@RequestBody body: RegisterDTO) : HttpEntity<String> {
        if (userRepository.findByEmail(body.email) != null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This email is already taken")

        if (!validationService.isValidEmail(body.email))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This email address is invalid")

        if (!validationService.isValidPassword(body.password)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("The password must be at least eight characters long, including uppercase and lowercase letters, digits, and special characters")
        }

        userRepository.save(
            User(
                nickname = body.nickname,
                email = body.email,
                password = passwordEncoder.encode(body.password),
                created = Timestamp.from(Instant.now())
            )
        )

        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully")
    }

    @Transactional
    fun loginUser(@RequestBody body: LoginDTO, response: HttpServletResponse) : HttpEntity<String> {
        val user = userRepository.findByEmail(body.email) ?:
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found")

        if (!user.comparePassword(body.password)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid password")
        }

        val issuer = user.id.toString()
        val expires = Timestamp(System.currentTimeMillis() + 60 * 24 * 1000) // 1 день

        val jwt = Jwts.builder()
            .setIssuer(issuer)
            .setExpiration(expires)
            .signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret)), SignatureAlgorithm.HS256)
            .compact()

        val cookie = Cookie("jwt", jwt)
        cookie.isHttpOnly = true

        sessionRepository.save(
            Session(
                userId = user.id,
                token = jwt,
                expires = expires
            )
        )

        response.addCookie(cookie)

        return ResponseEntity.status(HttpStatus.OK).body("User authenticated successfully")
    }

    @Transactional
    fun getUser(jwt: String?) : ResponseEntity<Any> {
        val user = fetchUserData(jwt) ?: return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unauthenticated")

        return ResponseEntity.status(HttpStatus.OK).body(user)
    }

    @Transactional
    fun userLogout(response: HttpServletResponse) : ResponseEntity<Any> {
        val cookie = Cookie("jwt", "")
        cookie.maxAge = 0

        response.addCookie(cookie)

        return ResponseEntity.status(HttpStatus.OK).body("success")
    }

    fun fetchUserData(jwt: String?) : User? {
        try {
            if (jwt == null) {
                return null
            }

            val body = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret)))
                .build()
                .parseClaimsJws(jwt)
                .body

            return this.userRepository.getById(body.issuer.toInt())
        } catch (e: Exception) {
            return null
        }
    }
}