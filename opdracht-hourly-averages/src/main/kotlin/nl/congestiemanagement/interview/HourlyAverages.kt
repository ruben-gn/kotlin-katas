package nl.congestiemanagement.interview

/**
 * Eén meetwaarde per kwartier: het redispatch-volume in MW.
 */
data class IspValue(val isp: Int, val volumeMw: Int?)

/**
 * Rekent kwartierwaarden om naar een gemiddelde per uur.
 *
 * isp = imbalance settlement period, kwartier van de dag, beginnend bij 1.
 * Het resultaat is per uur van de dag (0 = 00:00-01:00).
 */
class HourlyAverages {

    /** Gemiddeld volume per uur van de dag. */
    fun hourlyAverages(values: List<IspValue>): Map<Int, Double> {
        val sums = mutableMapOf<Int, Int>()
        val counts = mutableMapOf<Int, Int>()
        for (v in values) {
            val hour = (v.isp - 1) / 4
            sums[hour] = (sums[hour] ?: 0) + (v.volumeMw ?: 0)
            counts[hour] = (counts[hour] ?: 0) + 1
        }
        val result = mutableMapOf<Int, Double>()
        for (hour in sums.keys) {
            result[hour] = (sums[hour]!! / counts[hour]!!).toDouble()
        }
        return result
    }
}
