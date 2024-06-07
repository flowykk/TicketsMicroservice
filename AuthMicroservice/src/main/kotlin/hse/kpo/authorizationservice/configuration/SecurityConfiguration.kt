package hse.kpo.authorizationservice.configuration

import hse.kpo.authorizationservice.service.UserProfileService
import hse.kpo.authorizationservice.service.UserService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import kotlin.io.encoding.ExperimentalEncodingApi

@Configuration
@ExperimentalEncodingApi
class SecurityConfiguration(
    private val userProfileService: UserProfileService
) {
    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { it.disable() }
            .authorizeHttpRequests { c ->
                c
                    .requestMatchers("/**", "/home", "/login", "/register", "/logout", "/user").permitAll()
                    .anyRequest().authenticated()
            }
            .userDetailsService(userProfileService)

        return http.build()
    }
}