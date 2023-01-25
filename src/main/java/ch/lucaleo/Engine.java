package ch.lucaleo;

import de.dokchess.allgemein.Stellung;
import de.dokchess.allgemein.Zug;
import de.dokchess.regeln.DefaultSpielregeln;
import de.dokchess.regeln.Spielregeln;

import java.util.Collection;

public class Engine {
    public Engine() {
    }

    public boolean isCheckmateIn2MovesPossible(Stellung stellung){
        Spielregeln spielregeln = new DefaultSpielregeln();
        Collection<Zug> possibleMoves = spielregeln.liefereGueltigeZuege(stellung);
        possibleMoves.forEach(System.out::println);
        return false;
    }
}
