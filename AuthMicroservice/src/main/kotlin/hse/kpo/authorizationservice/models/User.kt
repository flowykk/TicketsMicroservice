package hse.kpo.authorizationservice.models

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
    private val created: Timestamp? = null
) {
    fun comparePassword(password: String): Boolean {
        return BCryptPasswordEncoder().matches(password, this.password);
    }

    fun getPassword(): String = password

    fun getUsername(): String = nickname

    fun getEmail(): String = email

    fun getCreated(): Timestamp? = created
}