package hse.kpo.ticketsservice.repository

import hse.kpo.ticketsservice.models.Station
import org.springframework.data.repository.CrudRepository

interface StationRepository : CrudRepository<Station, Int> {
    fun findByStation(station: String) : Station?
}