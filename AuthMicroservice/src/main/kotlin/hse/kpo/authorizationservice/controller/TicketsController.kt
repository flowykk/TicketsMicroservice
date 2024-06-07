package hse.kpo.authorizationservice.controller

import hse.kpo.authorizationservice.dtos.BuyTicketDTO
import hse.kpo.authorizationservice.dtos.StationDTO
import hse.kpo.authorizationservice.service.TicketService
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

    @GetMapping("get-orders")
    fun orders(@CookieValue jwt: String?) = ticketService.getOrders(jwt)
}