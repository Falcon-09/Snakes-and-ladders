package org.example.observer;

public class GameLogger implements GameObserver {
    @Override
    public void update(String message) {
        System.out.println(message);
    }
}
