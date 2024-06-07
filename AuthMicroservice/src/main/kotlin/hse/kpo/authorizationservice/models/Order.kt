package hse.kpo.authorizationservice.models

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.sql.Timestamp

@Table("orders")
class Order (
    @Id
    @Column("id")
    val id: Int? = null,
    @Column("user_id")
    private val user_id: Int? = null,
    @Column("from_station_id")
    private val from_station_id: Int,
    @Column("to_station_id")
    private val to_station_id: Int,
    @Column("status")
    private val status: String,
    @Column("created")
    private val created: Timestamp
) {

}