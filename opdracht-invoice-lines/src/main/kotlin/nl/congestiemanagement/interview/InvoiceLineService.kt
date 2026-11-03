package nl.congestiemanagement.interview

import java.math.BigDecimal
import java.time.LocalDate

/**
 * Maakt factuurregels van activaties van flex-contracten.
 *
 * Een activatie komt binnen als map, bijvoorbeeld:
 *   ["contractId": "C-1", "isp": 12, "volumeMwh": 2.5, "price": 40.0, "direction": "UP"]
 *
 * isp = imbalance settlement period (kwartier van de dag)
 * UP = netbeheerder betaalt de marktpartij, DOWN = marktpartij betaalt terug.
 */
class InvoiceLineService {

    // alle regels die we hebben aangemaakt
    val lines = mutableListOf<MutableMap<String, Any?>>()

    private var nextId = 1

    /** Maakt een factuurregel per activatie. */
    fun createLines(activations: List<Map<String, Any?>>, deliveryDate: LocalDate): List<Map<String, Any?>> {
        val result = mutableListOf<MutableMap<String, Any?>>()
        for (a in activations) {
            val volume = a["volumeMwh"] as Double
            val price = a["price"] as Double
            val sign = when (a["direction"]) {
                "UP" -> 1
                "DOWN" -> -1
                else -> 1
            }
            val amount = sign * Math.round(volume * price * 100) / 100.0
            val line = mutableMapOf<String, Any?>(
                "id" to nextId++,
                "contractId" to a["contractId"],
                "isp" to a["isp"],
                "date" to deliveryDate,
                "volumeMwh" to volume,
                "price" to price,
                "amount" to amount,
                "status" to "NEW",
            )
            result.add(line)
            lines.add(line)
        }
        return result
    }

    /** Totaalbedrag van een contract. */
    fun total(contractId: String): Double {
        var total = 0.0
        for (line in lines) {
            if (line["contractId"] == contractId) {
                total += line["amount"] as Double
            }
        }
        return total
    }

    /** Klopt het gefactureerde bedrag met ons totaal? */
    fun matchesInvoice(contractId: String, invoicedAmount: BigDecimal): Boolean {
        return BigDecimal(total(contractId)) == invoicedAmount
    }

    /** Regels van een contract inclusief 21% btw, voor de pdf. */
    fun linesInclVat(contractId: String): List<Map<String, Any?>> {
        return lines
            .filter { it["contractId"] == contractId }
            .map {
                it["amount"] = (it["amount"] as Double) * 1.21
                it
            }
    }
}
