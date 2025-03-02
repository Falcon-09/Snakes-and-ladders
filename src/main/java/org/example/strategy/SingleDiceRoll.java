package org.example.strategy;

import java.util.Random;

public class SingleDiceRoll implements DiceRollStrategy {
    @Override
    public int roll() {
        return new Random().nextInt(6) + 1;
    }
}
