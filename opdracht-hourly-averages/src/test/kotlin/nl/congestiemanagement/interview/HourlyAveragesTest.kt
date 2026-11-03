package nl.congestiemanagement.interview

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class HourlyAveragesTest {

    private val averages = HourlyAverages()

    @Test
    fun `gemiddelde per uur`() {
        val result = averages.hourlyAverages(
            listOf(
                IspValue(1, 10), IspValue(2, 20), IspValue(3, 30), IspValue(4, 40),
                IspValue(5, 8), IspValue(6, 8), IspValue(7, 8), IspValue(8, 8),
            ),
        )

        assertEquals(mapOf(0 to 25.0, 1 to 8.0), result)
    }

    @Test
    fun `uur met maar een paar kwartieren`() {
        val result = averages.hourlyAverages(listOf(IspValue(5, 10), IspValue(6, 30)))

        assertEquals(mapOf(1 to 20.0), result)
    }

    @Test
    fun `lege invoer geeft leeg resultaat`() {
        assertEquals(emptyMap<Int, Double>(), averages.hourlyAverages(emptyList()))
    }
}
