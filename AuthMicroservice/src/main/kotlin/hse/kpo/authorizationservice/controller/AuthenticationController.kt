package hse.kpo.authorizationservice.controller

import hse.kpo.authorizationservice.dtos.LoginDTO
import hse.kpo.authorizationservice.dtos.RegisterDTO
import hse.kpo.authorizationservice.models.User
import hse.kpo.authorizationservice.service.UserService
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CookieValue
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import kotlin.io.encoding.ExperimentalEncodingApi

@RestController
@RequestMapping
@ExperimentalEncodingApi
class AuthenticationController(
    private val userService: UserService
) {
    @PostMapping("register")
    fun register(@RequestBody body: RegisterDTO) = userService.registerUser(body)

    @PostMapping("login")
    fun login(@RequestBody body: LoginDTO, response: HttpServletResponse) = userService.loginUser(body, response)

    @GetMapping("user")
    fun user(@CookieValue("jwt") jwt: String?) = userService.getUser(jwt)

    @PostMapping("log-out")
    fun logout(response: HttpServletResponse) = userService.userLogout(response)
}