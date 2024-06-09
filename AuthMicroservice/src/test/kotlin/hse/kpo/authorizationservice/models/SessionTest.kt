package hse.kpo.authorizationservice.models

import SessionDataHelper
import org.junit.jupiter.api.Test

import java.sql.Timestamp
import java.time.Instant

class SessionTest {
    private val sessionDataHelper = SessionDataHelper()

    @Test
    fun getUserId_should_be_null() {
        assert(sessionDataHelper.getNullUserIdTestSession().getUserId() == null)
    }

    @Test
    fun getUserId_should_successfully_return_userId() {
        assert(sessionDataHelper.getTestDefaultSession().getUserId() == 1)
    }

    @Test
    fun getToken_should_successfully_return_token() {
        assert(sessionDataHelper.getTestDefaultSession().getToken() == "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c")
    }

    @Test
    fun getExpires_should_be_null() {
        assert(sessionDataHelper.getNullExpiresTestSession().getExpires() == null)
    }

    @Test
    fun getExpires_should_successfully_return_expiresTimestamp() {
        assert(sessionDataHelper.getTestDefaultSession().getExpires() != null)
        assert(sessionDataHelper.getTestDefaultSession().getExpires()!! < Timestamp.from(Instant.now()))
    }
}