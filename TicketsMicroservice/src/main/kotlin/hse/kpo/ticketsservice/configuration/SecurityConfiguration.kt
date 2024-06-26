package hse.kpo.ticketsservice.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import kotlin.io.encoding.ExperimentalEncodingApi

@Configuration
@ExperimentalEncodingApi
@EnableWebSecurity
class SecurityConfiguration(
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

        return http.build()
    }
}