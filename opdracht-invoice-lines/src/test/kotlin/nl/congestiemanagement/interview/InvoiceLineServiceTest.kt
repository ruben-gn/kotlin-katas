package nl.congestiemanagement.interview

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.time.LocalDate

class InvoiceLineServiceTest {

    private val service = InvoiceLineService()
    private val date = LocalDate.of(2026, 11, 1)

    @Test
    fun `maakt regels voor UP en DOWN`() {
        val lines = service.createLines(
            listOf(
                mapOf("contractId" to "C-1", "isp" to 12, "volumeMwh" to 2.5, "price" to 40.0, "direction" to "UP"),
                mapOf("contractId" to "C-1", "isp" to 13, "volumeMwh" to 1.0, "price" to 40.0, "direction" to "DOWN"),
            ),
            date,
        )

        assertEquals(2, lines.size)
        assertEquals(100.0, lines[0]["amount"])
        assertEquals(-40.0, lines[1]["amount"])
        assertEquals(60.0, service.total("C-1"))
    }

    @Test
    fun `regels inclusief btw`() {
        service.createLines(
            listOf(mapOf("contractId" to "C-1", "isp" to 12, "volumeMwh" to 2.5, "price" to 40.0, "direction" to "UP")),
            date,
        )

        val lines = service.linesInclVat("C-1")

        assertEquals(121.0, lines.single()["amount"])
    }

    @Test
    fun `factuurbedrag klopt`() {
        service.createLines(
            listOf(mapOf("contractId" to "C-1", "isp" to 12, "volumeMwh" to 2.5, "price" to 40.0, "direction" to "UP")),
            date,
        )

        assertTrue(service.matchesInvoice("C-1", BigDecimal(100)))
    }
}
