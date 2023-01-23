package org.example;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Board {
    private Map<String, String> positions;

    public Board(String fen) {
        positions = new HashMap<>();
        String[] fenParts = fen.split(" ");
        String[] rows = fenParts[0].split("/");

        // Iterate through the rows
        for (int i = 0; i < 8; i++) {
            int col = 0;
            // Iterate through the characters in the row
            for (int j = 0; j < rows[i].length(); j++) {
                char c = rows[i].charAt(j);
                // If it is a digit, it represents the number of empty squares
                if (Character.isDigit(c)) {
                    int numEmptySquares = Character.getNumericValue(c);
                    for (int k = 0; k < numEmptySquares; k++) {
                        String key = (char)('a' + col) + Integer.toString(8 - i);
                        // Add the empty squares to the positions map
                        positions.put(key, "");
                        col++;
                    }
                } else {
                    String key = (char)('a' + col) + Integer.toString(8 - i);
                    // Add the piece to the positions map
                    positions.put(key, Character.toString(c));
                    col++;
                }
            }
        }
    }

    public Map<String, String> getPositions() {
        return positions;
    }

    public String getPieceAtPosition(String position){
        return positions.get(position);
    }
}
