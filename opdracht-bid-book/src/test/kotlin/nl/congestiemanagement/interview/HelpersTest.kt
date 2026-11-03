package nl.congestiemanagement.interview

import java.time.Instant
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class HelpersTest {

    @Test
    fun `kwartierenTussen geeft de begintijden, tot is exclusief`() {
        assertEquals(
            listOf(
                Instant.parse("2026-06-15T08:00:00Z"),
                Instant.parse("2026-06-15T08:15:00Z"),
                Instant.parse("2026-06-15T08:30:00Z"),
                Instant.parse("2026-06-15T08:45:00Z"),
            ),
            kwartierenTussen(Instant.parse("2026-06-15T08:00:00Z"), Instant.parse("2026-06-15T09:00:00Z")),
        )
    }

    @Test
    fun `kwartierenTussen is leeg als van gelijk is aan tot`() {
        val acht = Instant.parse("2026-06-15T08:00:00Z")

        assertEquals(emptyList<Instant>(), kwartierenTussen(acht, acht))
    }
}
