package hse.kpo.authorizationservice.repository

import hse.kpo.authorizationservice.models.Session
import hse.kpo.authorizationservice.models.Station
import org.springframework.data.repository.CrudRepository

interface StationRepository : CrudRepository<Station, Int> {
    fun findByStation(station: String) : Station?
}