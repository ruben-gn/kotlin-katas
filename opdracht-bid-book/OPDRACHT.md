# Opdracht: Biedboek

`Biedboek` verzamelt de biedingen op een congestieverzoek en rekent uit hoeveel vermogen er is aangeboden en in welke kwartieren er nog een tekort is.

De code werkt (`./gradlew test` is groen), maar we zijn er niet blij mee.

We gaan hem samen verbeteren. Er is geen vast eindresultaat; we zijn benieuwd hoe je denkt.

- Lees de code hardop: wat valt je op?
- Wat zou je als eerste aanpakken, en waarom?
- Refactor in kleine stappen. Vragen stellen en opzoeken mag altijd.

## Domein in het kort

- Bij congestie vraagt een netbeheerder flexaanbieders om in een aantal kwartieren minder vermogen (MW) af te nemen.
- Flexaanbieders reageren met biedingen: per kwartier hoeveel MW ze minder kunnen afnemen.
- Tijden zijn `Instant`s. Een kwartier duurt 15 minuten en wordt aangeduid met zijn begintijd.
- `Helpers.kt` bevat hulpfuncties die kloppen en getest zijn; daar hoef je niets aan te doen.

_Sterk vereenvoudigd en verzonnen voor deze opdracht._
