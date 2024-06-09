package hse.kpo.ticketsservice.models

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("station")
class Station (
    @Id @Column("id")
    val id: Int? = null,
    @Column("station")
    private val station: String
) {
    fun getStation(): String = station
}