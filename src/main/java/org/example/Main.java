package org.example;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String initialPositions = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";
        Board board = new Board(initialPositions);
        Map<String, String> positions = board.getPositions();
        System.out.println(positions);
        System.out.println(board.getPieceAtPosition("b2"));

    }
}