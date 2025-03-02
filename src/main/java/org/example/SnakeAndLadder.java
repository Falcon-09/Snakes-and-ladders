package org.example;

import org.example.exception.InvalidInputException;
import org.example.observer.GameLogger;
import org.example.service.Game;
import org.example.strategy.SingleDiceRoll;

import java.util.Scanner;

public class SnakeAndLadder {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter number of rows for the board: ");
            int rows = scanner.nextInt();
            System.out.print("Enter number of columns for the board: ");
            int cols = scanner.nextInt();

            System.out.print("Enter number of players: ");
            int numPlayers = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            Game game = new Game(rows, cols, new SingleDiceRoll());
            game.addObserver(new GameLogger());

            for (int i = 0; i < numPlayers; i++) {
                System.out.print("Enter player " + (i + 1) + " name: ");
                String name = scanner.nextLine();
                game.addPlayer(name);
            }

            System.out.print("Enter number of snakes: ");
            int numSnakes = scanner.nextInt();
            for (int i = 0; i < numSnakes; i++) {
                System.out.print("Enter snake " + (i + 1) + " head (row col) and tail (row col): ");
                int headRow = scanner.nextInt();
                int headCol = scanner.nextInt();
                int tailRow = scanner.nextInt();
                int tailCol = scanner.nextInt();
                game.addSnake(headRow, headCol, tailRow, tailCol);
            }

            System.out.print("Enter number of ladders: ");
            int numLadders = scanner.nextInt();
            for (int i = 0; i < numLadders; i++) {
                System.out.print("Enter ladder " + (i + 1) + " start (row col) and end (row col): ");
                int startRow = scanner.nextInt();
                int startCol = scanner.nextInt();
                int endRow = scanner.nextInt();
                int endCol = scanner.nextInt();
                game.addLadder(startRow, startCol, endRow, endCol);
            }

            game.start();
        } catch (InvalidInputException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}