package hse.kpo.authorizationservice.service

import hse.kpo.authorizationservice.dtos.BuyTicketDTO
import hse.kpo.authorizationservice.dtos.StationDTO
import hse.kpo.authorizationservice.models.Order
import hse.kpo.authorizationservice.models.Station
import hse.kpo.authorizationservice.repository.OrderRepository
import hse.kpo.authorizationservice.repository.SessionRepository
import hse.kpo.authorizationservice.repository.StationRepository
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.CookieValue
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
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
    fun buy(@RequestBody body: BuyTicketDTO, @CookieValue jwt: String?) : HttpEntity<String> {
        if (jwt == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unauthenticated")

        val session = sessionRepository.findByToken(jwt) ?:
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User's session not found")
        if (session.getExpires() == null || session.getExpires()!! < Timestamp.from(Instant.now()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User's session got expired")

        val fromStationId = stationRepository.findByStation(body.from_station)?.id ?:
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("FromStation not found")
        val toStationId = stationRepository.findByStation(body.to_station)?.id ?:
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ToStation not found")
        if (toStationId == fromStationId)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Stations can't be same")

        orderRepository.save(
            Order(
                userId = session.getUserId(),
                from_station_id = fromStationId,
                to_station_id = toStationId,
                status = 1,
                created = Timestamp.from(Instant.now())
            )
        )

        return ResponseEntity.status(HttpStatus.OK).body("Your order was created successfully")
    }

    @Transactional
    fun addStation(@RequestBody body: StationDTO) : HttpEntity<String> {
        if (body.station.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Station's name can't be empty")

        if (body.station.length < 4)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Station's name must contain minimum 4 characters")

        if (stationRepository.findByStation(body.station) != null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Can't add duplicated station")

        stationRepository.save(
            Station(
                station = body.station
            )
        )

        return ResponseEntity.status(HttpStatus.OK).body("Station added successfully")
    }

    @Transactional
    fun getOrders(@CookieValue jwt: String?) : ResponseEntity<Any> {
        if (jwt == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unauthenticated")

        val session = sessionRepository.findByToken(jwt) ?:
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User's session not found")
        if (session.getExpires() == null || session.getExpires()!! < Timestamp.from(Instant.now()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User's session got expired")

        val orders = orderRepository.findByUserId(session.getUserId())

        return ResponseEntity.status(HttpStatus.OK).body(orders)
    }

    @Transactional
    fun getOrder(@RequestParam orderId: Int, @CookieValue jwt: String?) : ResponseEntity<Any> {
        if (jwt == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unauthenticated")

        val session = sessionRepository.findByToken(jwt) ?:
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User's session not found")
        if (session.getExpires() == null || session.getExpires()!! < Timestamp.from(Instant.now()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User's session got expired")

        val order = orderRepository.getById(orderId) ?:
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Order not found")

        if (order.getUserId() != session.getUserId())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You can't view someone's order")

        return ResponseEntity.status(HttpStatus.OK).body(order)
    }
}