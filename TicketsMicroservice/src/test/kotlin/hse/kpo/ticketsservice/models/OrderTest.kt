package hse.kpo.ticketsservice.models

import OrderDataHelper
import SessionDataHelper
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.sql.Timestamp
import java.time.Instant

class OrderTest {
    private val orderDataHelper = OrderDataHelper()

    @Test
    fun getUserId_should_be_null() {
        assert(orderDataHelper.getNullUserIdTestOrder().getUserId() == null)
    }

    @Test
    fun getUserId_should_successfully_return_userId() {
        assert(orderDataHelper.getTestDefaultOrder().getUserId() == 1)
    }

    @Test
    fun getFromStationId() {
        assert(orderDataHelper.getTestDefaultOrder().getFromStationId() == 1)
    }

    @Test
    fun getToStationId() {
        assert(orderDataHelper.getTestDefaultOrder().getToStationId() == 2)
    }

    @Test
    fun getStatus() {
        assert(orderDataHelper.getTestDefaultOrder().getStatus() == 1)
    }

    @Test
    fun getCreated_should_successfully_return_expiresTimestamp() {
        assert(orderDataHelper.getTestDefaultOrder().getCreated() < Timestamp.from(Instant.now()))
    }
}