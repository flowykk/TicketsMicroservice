package hse.kpo.authorizationservice.service

import hse.kpo.authorizationservice.dtos.BuyTicketDTO
import hse.kpo.authorizationservice.dtos.StationDTO
import hse.kpo.authorizationservice.models.Order
import hse.kpo.authorizationservice.models.Session
import hse.kpo.authorizationservice.models.Station
import hse.kpo.authorizationservice.repository.OrderRepository
import hse.kpo.authorizationservice.repository.SessionRepository
import hse.kpo.authorizationservice.repository.StationRepository
import hse.kpo.authorizationservice.repository.UserRepository
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.CookieValue
import org.springframework.web.bind.annotation.RequestBody
import java.sql.Timestamp
import java.time.Instant
import kotlin.io.encoding.ExperimentalEncodingApi

@Service
@ExperimentalEncodingApi
class TicketService(
    private val orderRepository: OrderRepository,
    private val stationRepository: StationRepository,
    private val sessionRepository: SessionRepository
) {
    @Transactional
    fun buy(@RequestBody body: BuyTicketDTO, @CookieValue jwt: String?) : ResponseEntity<Any> {
        if (jwt == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("unauthenticated")

        val session = sessionRepository.findByToken(jwt) ?:
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User's session not found")
        if (session.getExpires() == null || session.getExpires()!! < Timestamp.from(Instant.now()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User's session got expired")

        val order = Order(
            user_id = session.getUserId(),
            from_station_id = 1,
            to_station_id = 1,
            status = "created",
            created = Timestamp.from(Instant.now())
        )

        orderRepository.save(order)

        return ResponseEntity.status(HttpStatus.OK).body(order)
    }

    @Transactional
    fun addStation(@RequestBody body: StationDTO) : HttpEntity<String> {
        if (body.station.isEmpty())
            return ResponseEntity.status(HttpStatus.OK).body("Station's name can't be empty")

        if (body.station.length < 4)
            return ResponseEntity.status(HttpStatus.OK).body("Station's name must contain minimum 4 characters")

        if (stationRepository.findByStation(body.station) != null)
            return ResponseEntity.status(HttpStatus.OK).body("Can't add duplicated station")

        stationRepository.save(
            Station(
                station = body.station
            )
        )

        return ResponseEntity.status(HttpStatus.OK).body("Station added successfully")
    }
}