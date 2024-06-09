package hse.kpo.ticketsservice.repository

import hse.kpo.ticketsservice.models.Order
import org.springframework.data.repository.CrudRepository

interface OrderRepository : CrudRepository<Order, Int> {
    fun findByUserId(userId: Int?): List<Order>
    fun getById(id: Int): Order?
}