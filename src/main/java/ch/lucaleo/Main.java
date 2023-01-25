package ch.lucaleo;

import de.dokchess.allgemein.Stellung;
import de.dokchess.regeln.DefaultSpielregeln;
import de.dokchess.regeln.Spielregeln;


public class Main {
    public static void main(String[] args) {
/*
        String initialPositions = "r1bqk1nr/ppppbppp/8/4N3/6PP/8/PPPPP3/RNBQKB1R w - - 10 18";
*/
        Stellung stellung = new Stellung("r1bqk1nr/ppppbppp/8/4N3/6PP/8/PPPPP3/RNBQKB1R w - - 10 18");
        Spielregeln spielregeln = new DefaultSpielregeln();
        System.out.println(spielregeln.liefereGueltigeZuege(stellung));
        Engine engine = new Engine();
        engine.isCheckmateIn2MovesPossible(stellung);
    }
}