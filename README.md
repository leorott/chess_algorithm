# Matt in zwei Zügen

## Abstract
Dem Programm zeigt auf, ob und wie aus einem beliebigen Spielstand Schachmatt erreicht werden kann.
Die Ausgangslage wird als String in der FEN Notation gespeichert. Das Resultat wird in einem Markdown File gespeichert.

## Bescheib Algorithmus

Der Algorithmus läuft folgendermassen ab:

1. Der Algorithmus nutzt die Ausgangslage (`initialStellung`) und führt eine Iteration über alle erlaubten Züge der Ausgangslage aus (`moeglicheZuege`).
2. Jeder mögliche Zug wird ausgeführt (`teilzug1`) und es werden wiederum für jede neue Stellung alle möglichen Züge (`stellungNachTeilzug1`) ermittelt.
3. Die neuen möglichen Züge (`moeglicheZuegeNachTeilzug1`) werden dann wiederum alle ausgeführt etc. 
4. Dieser Prozess wird bis zum Teilzug 4 wiederholt.
5. Nach dem Teilzug 4 wird überprüft, ob Matt möglich ist und falls ja, werden alle vergangenen Teilzüge in eine Liste gespeichert.

Für die Repräsentation des Spielstandes, Ermittlung und Durchführung der Züge und das Überprüfen auf Matt,
haben wir die Packages `de.dokchess.allgemein` und `de.dokchess.regeln;` genutzt. Weitere Informationen zu Dokchess sind hier zu finden: [https://www.dokchess.de/](https://www.dokchess.de/)

## UML

![UML](/uml.png)
  

## Q & A:
### Was ist FEN?

Die Forsyth-Edwards-Notation ([FEN](https://de.wikipedia.org/wiki/Forsyth-Edwards-Notation)) ist eine Kurznotation, 
mit der jede beliebige Brettstellung im Schach niedergeschrieben werden kann.

Hier ist eine Liste der Buchstaben und der Figuren, die sie darstellen:

* `P`: Weisser Bauer
* `N`: Weisser Springer
* `B`: Weisser Läufer
* `R`: Weisser Turm
* `Q`: Weisse Königin
* `K`: Weisser König
* `p`: Schwarzer Bauer
* `n`: Schwarzer Springer
* `b`: Schwarzer Läufer
* `r`: Schwarzer Turm
* `q`: Schwarze Königin
* `k`: Schwarzer König
