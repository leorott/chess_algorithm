package org.example;

import java.util.HashMap;
import java.util.Map;

public class Board {
    private Map<String, String> positions;

    public Board(String fen) {
        positions = new HashMap<>();
        String[] fenParts = fen.split(" ");
        String[] rows = fenParts[0].split("/");

        for (int i = 0; i < 8; i++) {
            int col = 0;
            for (int j = 0; j < rows[i].length(); j++) {
                char c = rows[i].charAt(j);
                if (Character.isDigit(c)) {
                    int numEmptySquares = Character.getNumericValue(c);
                    for (int k = 0; k < numEmptySquares; k++) {
                        String key = (char)('a' + col) + Integer.toString(8 - i);
                        positions.put(key, "");
                        col++;
                    }
                } else {
                    String key = (char)('a' + col) + Integer.toString(8 - i);
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
