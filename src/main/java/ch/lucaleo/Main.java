package ch.lucaleo;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String initialPositions = "r1bqk1nr/ppppbppp/8/4N3/6PP/8/PPPPP3/RNBQKB1R w - - 10 18";
        Board board = new Board(initialPositions);
        Map<String, String> positions = board.getPositions();
        System.out.println(positions);
        System.out.println(board.getPieceAtPosition("b2"));

    }
}