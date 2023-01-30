package ch.lucaleo;

import de.dokchess.allgemein.Stellung;
import de.dokchess.allgemein.Zug;
import de.dokchess.regeln.DefaultSpielregeln;
import de.dokchess.regeln.Spielregeln;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Engine {
    public Engine() {

    }

    public List<List<Zug>> isCheckmateIn2MovesPossible(Stellung initialStellung){
        Spielregeln spielregeln = new DefaultSpielregeln();
        List<List<Zug>> mattZuege = new ArrayList<>();
        AtomicReference<Integer> index = new AtomicReference<>(0);
        Collection<Zug> moeglicheZuege = spielregeln.liefereGueltigeZuege(initialStellung);

        // Iteration ueber alle erlaubten Zuege der Ausgangslage
        moeglicheZuege.forEach((teilzug1) -> {
            index.getAndSet(index.get() + 1);
            Stellung stellungNachTeilzug1 = initialStellung.fuehreZugAus(teilzug1);
            Collection<Zug> possibleMovesAfterZug1 = spielregeln.liefereGueltigeZuege(stellungNachTeilzug1);

            // Iteration ueber alle erlaubten Zuege nach Teilzug 1
            possibleMovesAfterZug1.forEach((teilzug2) -> {
                index.getAndSet(index.get() + 1);
                Stellung stellungNachTeilzug2 = stellungNachTeilzug1.fuehreZugAus(teilzug2);
                Collection<Zug> possibleMovesAfterZug2 = spielregeln.liefereGueltigeZuege(stellungNachTeilzug2);

                // Iteration ueber alle erlaubten Zuege nach Teilzug 2
                possibleMovesAfterZug2.forEach((teilzug3) -> {
                    index.getAndSet(index.get() + 1);
                    Stellung stellungNachTeilzug3 = stellungNachTeilzug2.fuehreZugAus(teilzug3);
                    Collection<Zug> possibleMovesAfterZug3 = spielregeln.liefereGueltigeZuege(stellungNachTeilzug3);

                    // Iteration ueber alle erlaubten Zuege nach Teilzug 3
                    possibleMovesAfterZug3.forEach((teilzug4) -> {
                        index.getAndSet(index.get() + 1);
                        Stellung stellungNachTeilzug4 = stellungNachTeilzug3.fuehreZugAus(teilzug4);

                        // Ueberpruefen ob Matt moeglich ist
                        if (spielregeln.aufMattPruefen(stellungNachTeilzug4)){
                            List<Zug> zuege = new ArrayList<>();
                            zuege.add(teilzug1);
                            zuege.add(teilzug2);
                            zuege.add(teilzug3);
                            zuege.add(teilzug4);
                            // Alle Teilzuege zu liste hinzufuegen
                            mattZuege.add(zuege);
                        }
                    });
                });

            });
        });
        System.out.println("Anzahl Iterationen: " + index);
        return mattZuege;
    }
}
