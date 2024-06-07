package hse.kpo.authorizationservice.repository

import hse.kpo.authorizationservice.models.Order
import org.springframework.data.repository.CrudRepository

interface OrderRepository : CrudRepository<Order, Int> {

}