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
        Collection<Zug> moeglicheZuege = spielregeln.liefereGueltigeZuege(initialStellung);
        List<List<Zug>> mattZuege = new ArrayList<>();
        AtomicReference<Integer> index = new AtomicReference<>(0);

        moeglicheZuege.forEach((zug1) -> {
            index.getAndSet(index.get() + 1);
            Stellung stellungNachTeilzug1 = initialStellung.fuehreZugAus(zug1);
            Collection<Zug> possibleMovesAfterZug1 = spielregeln.liefereGueltigeZuege(stellungNachTeilzug1);

            possibleMovesAfterZug1.forEach((zug2) -> {
                index.getAndSet(index.get() + 1);
                Stellung stellungNachTeilzug2 = stellungNachTeilzug1.fuehreZugAus(zug2);
                Collection<Zug> possibleMovesAfterZug2 = spielregeln.liefereGueltigeZuege(stellungNachTeilzug2);

                possibleMovesAfterZug2.forEach((zug3) -> {
                    index.getAndSet(index.get() + 1);
                    Stellung stellungNachTeilzug3 = stellungNachTeilzug2.fuehreZugAus(zug3);
                    Collection<Zug> possibleMovesAfterZug3 = spielregeln.liefereGueltigeZuege(stellungNachTeilzug3);

                    possibleMovesAfterZug3.forEach((zug4) -> {
                        index.getAndSet(index.get() + 1);
                        Stellung stellungNachTeilzug4 = stellungNachTeilzug3.fuehreZugAus(zug4);

                        if (spielregeln.aufMattPruefen(stellungNachTeilzug4)){
                            List<Zug> zuege = new ArrayList<>();
                            zuege.add(zug1);
                            zuege.add(zug2);
                            zuege.add(zug3);
                            zuege.add(zug4);
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
