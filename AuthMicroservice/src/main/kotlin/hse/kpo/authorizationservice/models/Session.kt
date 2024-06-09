package hse.kpo.authorizationservice.models

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.sql.Timestamp

@Table("sessions")
class Session (
    @Id
    @Column("id")
    val id: Int? = null,
    @Column("user_id")
    private val userId: Int? = null,
    @Column("token")
    private val token: String,
    @Column("expires") @JsonIgnore
    private val expires: Timestamp? = null,
) {
    fun getUserId(): Int? = userId
    fun getToken(): String = token
    fun getExpires(): Timestamp? = expires
}