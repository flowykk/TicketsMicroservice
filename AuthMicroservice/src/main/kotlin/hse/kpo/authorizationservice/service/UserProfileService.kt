package hse.kpo.authorizationservice.service

import hse.kpo.authorizationservice.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserProfileService(
    private val userRepository: UserRepository
) : UserDetailsService {

    override fun loadUserByUsername(email: String): UserDetails =
        userRepository.findByEmail(email)
            ?: throw UsernameNotFoundException("Email: $email - not found")
}