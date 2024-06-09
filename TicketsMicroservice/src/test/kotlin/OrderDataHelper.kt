import hse.kpo.ticketsservice.models.Order

class OrderDataHelper {
    private val dataHelper = DataHelper()

    fun getTestDefaultOrder() = Order(
        userId = 1,
        from_station_id = 1,
        to_station_id = 2,
        status = 1,
        created = dataHelper.testTimestamp
    )

    fun getNullUserIdTestOrder() = Order(
        from_station_id = 1,
        to_station_id = 2,
        status = 1,
        created = dataHelper.testTimestamp
    )
}
