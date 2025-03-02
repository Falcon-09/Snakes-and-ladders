package org.example.model;

import org.example.exception.InvalidInputException;

import java.util.HashMap;
import java.util.Map;

public class GameBoard {
    private static GameBoard instance;
    private Map<String, String> snakes; // Maps "row,col" of head to "row,col" of tail
    private Map<String, String> ladders; // Maps "row,col" of start to "row,col" of end
    private int rows;
    private int cols;

    private GameBoard(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.snakes = new HashMap<>();
        this.ladders = new HashMap<>();
    }

    public static GameBoard getInstance(int rows, int cols) {
        if (instance == null) {
            instance = new GameBoard(rows, cols);
        }
        return instance;
    }

    public void addSnake(int headRow, int headCol, int tailRow, int tailCol) throws InvalidInputException {
        if (headRow < 0 || headRow >= rows || headCol < 0 || headCol >= cols ||
                tailRow < 0 || tailRow >= rows || tailCol < 0 || tailCol >= cols) {
            throw new InvalidInputException("Invalid snake position: positions must be within board limits.");
        }
        if (headRow <= tailRow && headCol <= tailCol) {
            throw new InvalidInputException("Invalid snake position: head must be after tail.");
        }
        String headKey = headRow + "," + headCol;
        String tailKey = tailRow + "," + tailCol;
        snakes.put(headKey, tailKey);
    }

    public void addLadder(int startRow, int startCol, int endRow, int endCol) throws InvalidInputException {
        if (startRow < 0 || startRow >= rows || startCol < 0 || startCol >= cols ||
                endRow < 0 || endRow >= rows || endCol < 0 || endCol >= cols) {
            throw new InvalidInputException("Invalid ladder position: positions must be within board limits.");
        }
        if (startRow >= endRow && startCol >= endCol) {
            throw new InvalidInputException("Invalid ladder position: start must be before end.");
        }
        String startKey = startRow + "," + startCol;
        String endKey = endRow + "," + endCol;
        ladders.put(startKey, endKey);
    }

    public String getNewPosition(int row, int col) {
        String currentKey = row + "," + col;
        String newKey = currentKey;

        // Check for snakes and ladders repeatedly
        boolean changed;
        do {
            changed = false;
            if (snakes.containsKey(newKey)) {
                System.out.println("Oops! Swallowed by a snake.");
                newKey = snakes.get(newKey);
                changed = true;
            }
            if (ladders.containsKey(newKey)) {
                System.out.println("Yay! Climbed a ladder.");
                newKey = ladders.get(newKey);
                changed = true;
            }
        } while (changed); // Repeat until no more snakes or ladders are encountered

        return newKey;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public int getBoardSize() {
        return rows * cols;
    }

    // Helper method to convert linear position to 2D coordinates
    public int[] get2DCoordinates(int position) {
        int row = (position - 1) / cols;
        int col = (position - 1) % cols;
        return new int[]{row, col};
    }

    // Helper method to convert 2D coordinates to linear position
    public int getLinearPosition(int row, int col) {
        return row * cols + col + 1;
    }
}
