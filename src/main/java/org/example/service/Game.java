package org.example.service;

import org.example.exception.InvalidInputException;
import org.example.model.GameBoard;
import org.example.model.Player;
import org.example.model.PlayerFactory;
import org.example.observer.GameObserver;
import org.example.strategy.DiceRollStrategy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Game {
    private List<Player> players;
    private GameBoard board;
    private DiceRollStrategy diceRollStrategy;
    private List<GameObserver> observers;
    private boolean isGameOver;
    private List<Player> winners;

    public Game(int rows, int cols, DiceRollStrategy diceRollStrategy) {
        this.board = GameBoard.getInstance(rows, cols);
        this.diceRollStrategy = diceRollStrategy;
        this.players = new ArrayList<>();
        this.observers = new ArrayList<>();
        this.isGameOver = false;
        this.winners = new ArrayList<>();
    }

    public void addObserver(GameObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers(String message) {
        for (GameObserver observer : observers) {
            observer.update(message);
        }
    }

    public void addPlayer(String name) {
        players.add(PlayerFactory.createPlayer(name));
    }

    public void addSnake(int headRow, int headCol, int tailRow, int tailCol) throws InvalidInputException {
        board.addSnake(headRow, headCol, tailRow, tailCol);
    }

    public void addLadder(int startRow, int startCol, int endRow, int endCol) throws InvalidInputException {
        board.addLadder(startRow, startCol, endRow, endCol);
    }

    public void start() {
        while (!isGameOver) {
            Iterator<Player> iterator = players.iterator();
            while (iterator.hasNext()) {
                Player player = iterator.next();
                playTurn(player);

                // Check if the player has won
                if (player.getPosition() == board.getBoardSize()) {
                    winners.add(player); // Add the player to the winners list
                    iterator.remove(); // Remove the player from the game

                    // Check if only one player is left
                    if (players.size() == 1) {
                        Player lastPlayer = players.get(0);
                        notifyObservers(lastPlayer.getName() + " is the last player and loses the game!");
                        isGameOver = true;
                        System.out.println("Here are the results:");
                        announceResults();
                        return;
                    }
                }
            }
        }
    }

    private void playTurn(Player player) {
        int diceValue = diceRollStrategy.roll();
        int currentPosition = player.getPosition();
        int newPosition = currentPosition + diceValue;

        // Check if the new position is beyond the board
        if (newPosition > board.getBoardSize()) {
            notifyObservers(player.getName() + " rolled a " + diceValue + " and stays at " + currentPosition);
            return;
        }

        // Convert linear positions to 2D coordinates
        int[] currentCoords = board.get2DCoordinates(currentPosition);
        int[] newCoords = board.get2DCoordinates(newPosition);

        // Update position and check for snakes/ladders
        String newKey = board.getNewPosition(newCoords[0], newCoords[1]);
        String[] newKeyParts = newKey.split(",");
        int newRow = Integer.parseInt(newKeyParts[0]);
        int newCol = Integer.parseInt(newKeyParts[1]);
        newPosition = board.getLinearPosition(newRow, newCol);
        player.setPosition(newPosition);

        // Convert new position back to 2D coordinates for logging
        int[] finalCoords = board.get2DCoordinates(newPosition);

        notifyObservers(player.getName() + " rolled a " + diceValue + " and moved from (" + currentCoords[0] + "," + currentCoords[1] + ") to (" + finalCoords[0] + "," + finalCoords[1] + ")");
    }

    private void announceResults() {
        if (winners.size() > 0) {
            notifyObservers(winners.get(0).getName() + " is the first player to win the game!");
        }
        if (winners.size() > 1) {
            for (int i = 1; i < winners.size(); i++) {
                notifyObservers(winners.get(i).getName() + " is " + (i+1));
            }
        }
    }
}
