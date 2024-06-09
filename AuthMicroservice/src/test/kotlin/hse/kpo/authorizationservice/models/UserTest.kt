package hse.kpo.authorizationservice.models

import UserDataHelper
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.sql.Timestamp
import java.time.Instant

class UserTest {
    private val userDataHelper = UserDataHelper()

    @Test
    fun comparePassword() {
        assert(userDataHelper.getDefaultUser().comparePassword("Flowykk123!"))
    }

    @Test
    fun getPassword() {
        assert(userDataHelper.getDefaultUser().getPassword() == "\$2a\$10\$sSP6x8tcz5uqHAJG.Ur60eBVwLxegh7zpNCgo3POuvNygVjJPDWAu")
    }

    @Test
    fun getUsername() {
        assert(userDataHelper.getDefaultUser().getUsername() == "nickname")
    }

    @Test
    fun getEmail() {
        assert(userDataHelper.getDefaultUser().getEmail() == "ex@ex.com")
    }

    @Test
    fun getCreated_should_be_null() {
        assert(userDataHelper.getNullCreatedUser().getCreated() == null)
    }

    @Test
    fun getCreated_should_successfully_return_expiresTimestamp() {
        assert(userDataHelper.getDefaultUser().getCreated() != null)
        assert(userDataHelper.getDefaultUser().getCreated()!! < Timestamp.from(Instant.now()))
    }
}