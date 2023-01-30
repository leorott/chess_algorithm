package ch.lucaleo;

import de.dokchess.allgemein.Stellung;
import de.dokchess.allgemein.Zug;
import de.dokchess.regeln.DefaultSpielregeln;
import de.dokchess.regeln.Spielregeln;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Engine {
    public Engine() {
    }

    public List<Zug> isCheckmateIn2MovesPossible(Stellung initialStellung){
        Spielregeln spielregeln = new DefaultSpielregeln();
        Collection<Zug> possibleMoves = spielregeln.liefereGueltigeZuege(initialStellung);
        List<Zug> mattZuege = new ArrayList<Zug>();
        possibleMoves.forEach((zug1) -> {
            Stellung stellungNachZug1 = initialStellung.fuehreZugAus(zug1);

            Collection<Zug> possibleMovesAfterZug1 = spielregeln.liefereGueltigeZuege(stellungNachZug1);

            possibleMovesAfterZug1.forEach((zug2) -> {
                Stellung stellungNachZug2 = stellungNachZug1.fuehreZugAus(zug2);

                Collection<Zug> possibleMovesAfterZug2 = spielregeln.liefereGueltigeZuege(stellungNachZug2);
                possibleMovesAfterZug2.forEach((zug3) -> {
                    Stellung stellungNachZug3 = stellungNachZug2.fuehreZugAus(zug3);

                    Collection<Zug> possibleMovesAfterZug3 = spielregeln.liefereGueltigeZuege(stellungNachZug3);
                    possibleMovesAfterZug3.forEach((zug4) -> {
                        Stellung stellungNachZug4 = stellungNachZug3.fuehreZugAus(zug4);
                        if (spielregeln.aufMattPruefen(stellungNachZug4)){
                            System.out.println("schach!");
                            mattZuege.add(zug4);

                        }
                    });
                });

            });
        });
        return mattZuege;
    }
}
