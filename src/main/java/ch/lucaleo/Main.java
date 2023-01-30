package ch.lucaleo;

import de.dokchess.allgemein.Stellung;
import de.dokchess.allgemein.Zug;
import java.util.List;


public class Main {
    public static void main(String[] args) {
/*
        String initialPositions = "r1bqk1nr/ppppbppp/8/4N3/6PP/8/PPPPP3/RNBQKB1R w - - 10 18";
*/
        Stellung stellung = new Stellung("r1bqk1nr/ppppbppp/8/4N3/6PP/8/PPPPP3/RNBQKB1R w - - 10 18");
        Engine engine = new Engine();
        List<List<Zug>> mattZuege = engine.isCheckmateIn2MovesPossible(stellung);

        if (mattZuege.size() >= 1){
            System.out.println("Matt in zwei Zuegen ist mit folgenen Zuegen moeglich");
            mattZuege.forEach(System.out::println);

        } else {
            System.out.println("Matt ist nicht möglich");
        }
    }
}