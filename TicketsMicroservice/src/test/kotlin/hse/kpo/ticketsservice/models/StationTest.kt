package hse.kpo.ticketsservice.models

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class StationTest {
    @Test
    fun getStation() {
        val station = Station(station = "Moscow")

        assert(station.getStation() == "Moscow")
    }
}