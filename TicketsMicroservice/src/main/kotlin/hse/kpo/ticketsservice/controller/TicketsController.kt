package hse.kpo.ticketsservice.controller

import hse.kpo.ticketsservice.dtos.BuyTicketDTO
import hse.kpo.ticketsservice.dtos.StationDTO

import hse.kpo.ticketsservice.service.TicketService
import org.springframework.web.bind.annotation.*
import kotlin.io.encoding.ExperimentalEncodingApi

@RestController
@RequestMapping("tickets")
@ExperimentalEncodingApi
class TicketsController(
    private val ticketService: TicketService
) {
    @PostMapping("buy")
    fun buy(@RequestBody body: BuyTicketDTO, @CookieValue jwt: String?) = ticketService.buy(body, jwt)

    @PostMapping("add-station")
    fun addStation(@RequestBody body: StationDTO) = ticketService.addStation(body)

    @GetMapping("get-stations")
    fun getStations() = ticketService.getStations()

    @GetMapping("get-orders")
    fun orders(@CookieValue jwt: String?) = ticketService.getOrders(jwt)

    @GetMapping("get-order")
    fun order(@RequestParam orderId: Int, @CookieValue jwt: String?) = ticketService.getOrder(orderId, jwt)
}