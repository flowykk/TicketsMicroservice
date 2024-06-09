import com.fasterxml.jackson.annotation.JsonIgnore
import hse.kpo.authorizationservice.models.User
import org.springframework.data.relational.core.mapping.Column
import java.sql.Timestamp
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class UserDataHelper {
    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS")
    private val dateTimeString = "2024-06-09 00:00:00.000000"
    private val localDateTime = LocalDateTime.parse(dateTimeString, formatter)

    private val testTimestamp = Timestamp.valueOf(localDateTime)

    fun getDefaultUser() = User(
        nickname = "nickname",
        email = "ex@ex.com",
        password = "\$2a\$10\$sSP6x8tcz5uqHAJG.Ur60eBVwLxegh7zpNCgo3POuvNygVjJPDWAu",
        created = testTimestamp
    )

    fun getNullCreatedUser() = User(
        nickname = "nickname",
        email = "ex@ex.com",
        password = "\$2a\$10\$sSP6x8tcz5uqHAJG.Ur60eBVwLxegh7zpNCgo3POuvNygVjJPDWAu"
    )
}