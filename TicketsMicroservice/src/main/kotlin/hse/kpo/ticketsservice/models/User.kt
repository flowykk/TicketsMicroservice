package hse.kpo.ticketsservice.models

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.sql.Timestamp

@Table("users")
class User(
    @Id
    @Column("id")
    val id: Int? = null,
    @Column("nickname")
    private val nickname: String,
    @Column("email")
    private val email: String,
    @Column("password") @JsonIgnore
    private val password: String,
    @Column("created")
    val created: Timestamp? = null
) : UserDetails {

    fun comparePassword(password: String): Boolean {
        return BCryptPasswordEncoder().matches(password, this.password);
    }

    override fun getAuthorities(): Collection<GrantedAuthority>  = mutableListOf()

    override fun getPassword(): String = password

    override fun getUsername(): String = nickname

    fun getEmail(): String = email

    override fun isAccountNonExpired(): Boolean {
        // Можно добавить логику для проверки срока действия аккаунта
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        // Можно добавить логику для проверки блокировки аккаунта
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        // Можно добавить логику для проверки срока действия учетных данных
        return true
    }

    override fun isEnabled(): Boolean {
        // Можно добавить логику для проверки активации аккаунта
        return true
    }
}