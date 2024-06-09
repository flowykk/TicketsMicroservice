import hse.kpo.ticketsservice.models.Order
import hse.kpo.ticketsservice.models.Session
import java.sql.Timestamp
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class OrderDataHelper {
    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS")
    private val dateTimeString = "2024-06-09 00:00:00.000000"
    private val localDateTime = LocalDateTime.parse(dateTimeString, formatter)

    private val testTimestamp = Timestamp.valueOf(localDateTime)

    fun getTestTimestamp() = testTimestamp;
    fun getTestToken() = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";

    fun getTestDefaultOrder() = Order(
        userId = 1,
        from_station_id = 1,
        to_station_id = 2,
        status = 1,
        created = testTimestamp
    )

    fun getNullUserIdTestOrder() = Order(
        from_station_id = 1,
        to_station_id = 2,
        status = 1,
        created = testTimestamp
    )
}