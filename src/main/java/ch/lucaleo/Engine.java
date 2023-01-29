package ch.lucaleo;

import de.dokchess.allgemein.Stellung;
import de.dokchess.allgemein.Zug;
import de.dokchess.regeln.DefaultSpielregeln;
import de.dokchess.regeln.Spielregeln;

import java.util.Collection;

public class Engine {
    public Engine() {
    }

    public boolean isCheckmateIn2MovesPossible(Stellung initialStellung){
        Spielregeln spielregeln = new DefaultSpielregeln();
        Collection<Zug> possibleMoves = spielregeln.liefereGueltigeZuege(initialStellung);
        possibleMoves.forEach((zug1) -> {
            Stellung stellungNachZug1 = initialStellung.fuehreZugAus(zug1);
            System.out.println("Nach Zug 1:" + spielregeln.aufMattPruefen(stellungNachZug1));

            Collection<Zug> possibleMovesAfterZug1 = spielregeln.liefereGueltigeZuege(stellungNachZug1);

            possibleMovesAfterZug1.forEach((zug2) -> {
                Stellung stellungNachZug2 = stellungNachZug1.fuehreZugAus(zug2);
                System.out.println("Nach Zug 2:" + spielregeln.aufMattPruefen(stellungNachZug2));

                Collection<Zug> possibleMovesAfterZug2 = spielregeln.liefereGueltigeZuege(stellungNachZug2);
                possibleMovesAfterZug2.forEach((zug3) -> {
                    Stellung stellungNachZug3 = stellungNachZug2.fuehreZugAus(zug3);
                    System.out.println("Nach Zug 3: " + spielregeln.aufMattPruefen(stellungNachZug3));

                    Collection<Zug> possibleMovesAfterZug3 = spielregeln.liefereGueltigeZuege(stellungNachZug3);
                    possibleMovesAfterZug3.forEach((zug4) -> {
                        Stellung stellungNachZug4 = stellungNachZug3.fuehreZugAus(zug4);
                        System.out.println("Nach Zug 4: " + spielregeln.aufMattPruefen(stellungNachZug4));
                    });
                });

            });
        });
        return false;
    }
}
