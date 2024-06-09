package hse.kpo.ticketsservice.models

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.sql.Timestamp

@Table("sessions")
class Session1 (
    @Id
    @Column("id")
    val id: Int? = null,
    @Column("user_id")
    private val user_id: Int? = null,
    @Column("token")
    private val token: String,
    @Column("expires") @JsonIgnore
    private val expires: Timestamp? = null,
) {

}