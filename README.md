![image](https://github.com/user-attachments/assets/c2742825-24b8-41dc-b096-d5ca2c49a2d7)

# Snake and Ladder Game

A Java implementation of the classic Snake and Ladder game, designed with a 2D matrix board. The game supports multiple players, snakes, and ladders, and runs until only one player remains. The first player to reach the end of the board wins, while the last player loses.

## Features

- **2D Matrix Board:** The board is represented as a 2D matrix, with positions converted between linear and 2D coordinates.
- **Multiple Players:** Supports any number of players.
- **Snakes and Ladders:** Players can be bitten by multiple snakes or climb multiple ladders in succession.
- **Player Elimination:** Players are eliminated as they reach the winning condition, and the game continues until only one player remains.
- **Production-Grade Code:** Follows SOLID principles, uses design patterns (Factory Method, Observer), and includes exception handling.

## How to Play

### Set Up the Board:
- Specify the number of rows and columns for the board.
- Add players by entering their names.
- Add snakes and ladders by specifying their start and end positions.

### Gameplay:
- Players take turns rolling a dice.
- Based on the dice value, players move forward on the board.
- If a player lands on a snake's head, they slide down to the tail.
- If a player lands at the start of a ladder, they climb up to the end.
- The game continues until only one player remains.

### Winning:
- The first player to reach the end of the board wins.
- The last remaining player loses.

## Code Structure

The project is organized into the following packages:

- `com.snakeandladder.model`: Contains classes for the game board, players, and player factory.
- `com.snakeandladder.strategy`: Contains the dice roll strategy interface and implementations.
- `com.snakeandladder.observer`: Contains the observer interface and logger implementation.
- `com.snakeandladder.service`: Contains the main game logic.
- `com.snakeandladder.exception`: Contains custom exceptions for invalid inputs.

## Setup and Run

### Prerequisites
- Java Development Kit (JDK) 8 or higher.
- A terminal or command prompt.

## Example Input
```text
Enter number of rows for the board: 10
Enter number of columns for the board: 10
Enter number of players: 3
Enter player 1 name: Alice
Enter player 2 name: Bob
Enter player 3 name: Charlie
Enter number of snakes: 2
Enter snake 1 head (row col) and tail (row col): 1 4 0 2
Enter snake 2 head (row col) and tail (row col): 9 9 0 0
Enter number of ladders: 2
Enter ladder 1 start (row col) and end (row col): 0 5 2 5
Enter ladder 2 start (row col) and end (row col): 4 5 7 8
```

## Example Output
```text
Alice rolled a 5 and moved from (0,0) to (0,5)
Yay! Climbed a ladder.
Alice rolled a 5 and moved from (0,5) to (2,5)
Bob rolled a 3 and moved from (0,0) to (0,3)
Charlie rolled a 6 and moved from (0,0) to (0,6)
...
Alice rolled a 6 and moved from (9,4) to (9,10)
Alice wins the game and is eliminated!
Bob rolled a 6 and moved from (9,0) to (9,6)
Charlie rolled a 4 and moved from (9,5) to (9,9)
Oops! Swallowed by a snake.
Charlie rolled a 4 and moved from (9,9) to (0,0)
Bob rolled a 4 and moved from (9,6) to (9,10)
Bob wins the game and is eliminated!
Charlie is the last player and loses the game!
```

## Design Patterns Used

- **Factory Method Pattern:** Used in `PlayerFactory` to create player objects.
- **Observer Pattern:** Used for logging game events. The `GameLogger` class implements the `GameObserver` interface.
- **Singleton Pattern:** Used in `GameBoard` to ensure only one instance of the board exists.
- **Strategy Pattern:** Used for dice rolling. The `DiceRollStrategy` interface allows for different dice roll implementations (e.g., single dice, double dice).

## Contributing

Contributions are welcome! If you'd like to contribute, please follow these steps:

    1. Fork the repository.
    2. Create a new branch for your feature or bugfix.
    3. Commit your changes and push to your branch.
    4. Submit a pull request.

## License

This project is licensed under the MIT License. See the `LICENSE` file for details.

## Contact

For questions or feedback, please open an issue on GitHub or contact the maintainer:

**Nipun Mitra**    
GitHub: [github](https://github.com/Falcon-09)

Enjoy the game! 🎲🐍🪜
