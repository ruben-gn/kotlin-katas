package nl.congestiemanagement.interview

import java.time.Instant
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class BiedboekTest {

    private val verzoek = Verzoek(
        van = Instant.parse("2026-06-15T08:00:00Z"),
        tot = Instant.parse("2026-06-15T09:00:00Z"),
        nodigMw = 10,
    )

    @Test
    fun `telt het aangeboden vermogen op`() {
        val boek = Biedboek(verzoek)
        boek.ontvang(listOf(
            Bod("Kas Westland", Instant.parse("2026-06-15T08:00:00Z"), 4),
            Bod("Koelhuis Noord", Instant.parse("2026-06-15T08:15:00Z"), 6),
        ))
        boek.ontvang(listOf(
            Bod("Batterij Zuid", Instant.parse("2026-06-15T08:45:00Z"), 5),
            Bod("Kas Westland", Instant.parse("2026-06-15T08:30:00Z"), 0),
        ))

        assertEquals(15, boek.aangebodenMwVoorVerzoek())
    }

    @Test
    fun `zonder biedingen is er in elk kwartier een tekort`() {
        val boek = Biedboek(verzoek)

        assertEquals(
            mapOf(
                Instant.parse("2026-06-15T08:00:00Z") to 10,
                Instant.parse("2026-06-15T08:15:00Z") to 10,
                Instant.parse("2026-06-15T08:30:00Z") to 10,
                Instant.parse("2026-06-15T08:45:00Z") to 10,
            ),
            boek.tekortPerKwartier(),
        )
    }

    @Test
    fun `tekort alleen in kwartieren met te weinig aanbod`() {
        val boek = Biedboek(verzoek)
        boek.ontvang(
            listOf(
                Bod("Kas Westland", Instant.parse("2026-06-15T08:00:00Z"), 4),
                Bod("Koelhuis Noord", Instant.parse("2026-06-15T08:00:00Z"), 2),
                Bod("Koelhuis Noord", Instant.parse("2026-06-15T08:15:00Z"), 10),
                Bod("Batterij Zuid", Instant.parse("2026-06-15T08:30:00Z"), 3),
                Bod("Batterij Zuid", Instant.parse("2026-06-15T08:45:00Z"), 12),
            )
        )

        assertEquals(mapOf(
            Instant.parse("2026-06-15T08:00:00Z") to 4,
            Instant.parse("2026-06-15T08:30:00Z") to 7), boek.tekortPerKwartier(),
        )
    }
}
