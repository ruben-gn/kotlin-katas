# Opdracht: uurgemiddelden

`HourlyAverages` rekent kwartierwaarden om naar een gemiddelde per uur. De code werkt
(`./gradlew test` is groen), maar we zijn er niet blij mee.

We gaan hem samen verbeteren. Er is geen vast eindresultaat; we zijn benieuwd hoe je denkt.

- Lees de code hardop door: wat valt je op?
- Wat zou je als eerste aanpakken, en waarom?
- Refactor in kleine stappen. Vragen stellen en dingen opzoeken mag altijd.

## Domein in het kort

- Per kwartier (`isp`) is er een redispatch-volume in MW: hoeveel vermogen er op verzoek van
  de netbeheerder is op- of afgeregeld (positief = opgeregeld, negatief = afgeregeld).
- Voor een rapportage willen we per uur het gemiddelde.
