# The Forgotten Ruins - Java RPG

The player wakes up at the entrance to an ancient dungeon. To reach the
final treasure, they must explore several rooms, solve puzzles, battle
creatures, and manage their resources.

## Project goal

This project is being built step by step as a Java role-playing game. The
first version will be a **console application** so that the game rules can be
implemented and tested independently from the user interface.

The initial adventure will include:

- exploration of connected dungeon rooms;
- locked doors and puzzles;
- traps and turn-based combat;
- health, potions, keys, and quest items;
- victory, defeat, and the ability to start a new game.

The detailed MVP requirements are available in
[docs/REQUIREMENTS.md](docs/REQUIREMENTS.md).

## Development approach

The project is being developed in small, testable steps:

1. implement and test the core domain entities;
2. implement game state, rooms, and navigation;
3. implement puzzles, hints, and traps;
4. implement turn-based combat and item usage;
5. connect the game rules to the console input/output;
6. test the complete console experience.

The game rules will remain separated from console input and output as much as
possible. This will make the behavior easier to test and allow the console
interface to be replaced later without rewriting the game logic.

## Future interface

After the console version is complete, the project may receive a graphical or
windowed interface. That interface will reuse the existing game state and
domain rules rather than duplicating gameplay logic.

Possible future interface technologies include JavaFX or another Java desktop
UI toolkit. The graphical version is outside the current console MVP scope.

## Technology

- Java 21 (LTS)
- Maven
- JUnit 5

## Current status

The current foundation includes player health and healing rules, enemy
behavior, player inventory access, items, inventory management, and a game
state that supports active, victory, defeat, and new-game lifecycle states.
The first five-room dungeon layout, connected navigation, locked exits, and
the first three-attempt riddle puzzle, its damage trap, and basic turn-based
attack, defense, and potion combat are now in place. Defense reduces the next
enemy attack by half. Potions restore 25 health and are not consumed when the
player is already at full health. The console game loop, remaining combat
actions, and dungeon systems will be added next.
