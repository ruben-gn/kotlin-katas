package nl.congestiemanagement.interview

import java.time.Instant

/** Aanbod van een marktpartij: zoveel MW in het kwartier dat begint op kwartierStart. */
data class Bod(val aanbieder: String, val kwartierStart: Instant, val mw: Int)

/** Vraag om nodigMw in elk kwartier vanaf van tot (niet tot en met) tot. */
data class Verzoek(val van: Instant, val tot: Instant, val nodigMw: Int)

/** Verzamelt de biedingen op één verzoek. */
class Biedboek(private val verzoek: Verzoek) {
    private val biedingen = mutableListOf<Bod>()

    /** Neemt nieuwe biedingen op. */
    fun ontvang(nieuweBiedingen: List<Bod>) {
        nieuweBiedingen.forEach { bod ->
            if (bod.mw <= 0) return
            biedingen.add(bod)
        }
    }

    /** Hoeveel MW er in totaal is aangeboden voor de kwartieren van het verzoek. */
    fun aangebodenMwVoorVerzoek(): Int {
        var totaal = 0
        for (bod in biedingen) {
            totaal += bod.mw
        }
        return totaal
    }

    /** Per kwartier hoeveel MW er nog ontbreekt. */
    fun tekortPerKwartier(): Map<Instant, Int> {
        val tekort = mutableMapOf<Instant, Int>()
        for (kwartierStart in kwartierenTussen(verzoek.van, verzoek.tot)) {
            var aangeboden = 0
            for (bod in biedingen) {
                if (bod.kwartierStart == kwartierStart) aangeboden += bod.mw
            }
            if (aangeboden < verzoek.nodigMw) tekort[kwartierStart] = verzoek.nodigMw - aangeboden
        }
        return tekort
    }
}
