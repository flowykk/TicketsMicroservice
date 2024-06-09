import java.sql.Timestamp
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DataHelper {
    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS")
    private val dateTimeString = "2024-06-09 00:00:00.000000"
    private val localDateTime = LocalDateTime.parse(dateTimeString, formatter)

    val testTimestamp = Timestamp.valueOf(localDateTime)
    val testToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c"
}