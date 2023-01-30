package ch.lucaleo;

import de.dokchess.allgemein.Stellung;
import de.dokchess.allgemein.Zug;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        // Ausgangslage
        String fen = "r1bqk1nr/ppppbppp/8/4N3/6PP/8/PPPPP3/RNBQKB1R w - - 10 18";
        Stellung stellung = new Stellung(fen);
        Engine engine = new Engine();

        // Liste aller moeglichen Zugskombinationen mit denen Matt erreicht werden kann
        List<List<Zug>> mattZuege = engine.isCheckmateIn2MovesPossible(stellung);

        FileHandler fileHandler = new FileHandler();
        String fileName = fileHandler.createFile("Output.md");

        if (mattZuege.size() >= 1) {
            // Matt in 2 Zuegen ist moeglich
            String fileContent = "# Output\n\n Ausgehend von folgendem Spielstand: **\""
                    + fen + "\"** ist Schachmatt in 2 Zügen auf folgende Arten möglich: \n \n";

            for (List<Zug> zuege : mattZuege) {
                fileContent += "* " + zuege + "\n";
            }

            fileHandler.writeToFile(fileName, fileContent);
        } else {
            // Matt in 2 Zuegen ist nicht moeglich
            fileHandler.writeToFile(fileName, "Matt in 2 Zügen ist nicht möglich");
        }
    }
}