package hse.kpo.ticketsservice.controller

import hse.kpo.ticketsservice.dtos.RegisterDTO
import hse.kpo.ticketsservice.service.UserService
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
    @PostMapping("hello")
    fun register() = userService.registerUser()
}