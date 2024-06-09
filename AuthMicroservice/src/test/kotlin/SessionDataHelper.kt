import hse.kpo.authorizationservice.models.Session

class SessionDataHelper {
    private val dataHelper = DataHelper()

    fun getTestDefaultSession() = Session(
        userId = 1,
        token = dataHelper.testToken,
        expires = dataHelper.testTimestamp
    )

    fun getNullUserIdTestSession() = Session(
        token = dataHelper.testToken,
        expires = dataHelper.testTimestamp
    )

    fun getNullExpiresTestSession() = Session(
        userId = 1,
        token = dataHelper.testToken,
    )
}