# Opdracht: factuurregels

`InvoiceLineService` maakt factuurregels van activaties van flex-contracten. De code werkt
(`./gradlew test` is groen), maar we zijn er niet blij mee.

We gaan hem samen verbeteren. Er is geen vast eindresultaat; we zijn benieuwd hoe je denkt.

- Lees de code hardop door: wat valt je op?
- Wat zou je als eerste aanpakken, en waarom?
- Refactor in kleine stappen. Vragen stellen en dingen opzoeken mag altijd.

## Domein in het kort

- Een **activatie**: een marktpartij heeft op verzoek van de netbeheerder in een kwartier (`isp`)
  een hoeveelheid energie (`volumeMwh`) extra of minder geleverd, tegen een prijs (€/MWh).
- `UP`: netbeheerder betaalt de marktpartij. `DOWN`: marktpartij betaalt terug.
- Een **factuurregel** is de basis voor wat er gefactureerd wordt.
- Op de pdf voor de marktpartij staan bedragen inclusief 21% btw.
