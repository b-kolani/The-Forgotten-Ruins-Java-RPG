package com.biman.ruines.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import com.biman.ruines.game.Combat;
import com.biman.ruines.game.GameState;
import com.biman.ruines.game.Puzzle;

public class ConsoleGame {

    private final BufferedReader input;
    private final PrintStream output;
    private final GameState gameState;

    public ConsoleGame(BufferedReader input, PrintStream output) {
        this.input = input;
        this.output = output;
        this.gameState = new GameState();
    }

    public static void main(String[] args) {
        ConsoleGame game = new ConsoleGame(
            new BufferedReader(new InputStreamReader(System.in)),
            System.out
        );
        game.run();
    }

    public void run() {
        output.println("Welcome to The Forgotten Ruins.");

        try {
            boolean playAgain = true;
            while (playAgain) {
                playCurrentGame();
                showGameResult();

                // Stops if the game ended for any reason other than winning or losing
                // such as input being closed or an exception being thrown. Normally, 
                // the game should only end with a win or loss, but this is a safeguard.
                if (!gameState.hasWon() && !gameState.hasLost()) {
                    return;
                }

                output.println("Start a new game? (y/n)");
                String choice = input.readLine();
                if (!"y".equalsIgnoreCase(choice == null ? "" : choice.trim())) {
                    return;
                }

                gameState.startNewGame();
            }
        } catch (IOException exception) {
            output.println("Input error. The game has ended.");
        }
    }

    private void playCurrentGame() throws IOException {
        while (gameState.isActive()) {
            if (gameState.getCurrentCombat() != null) {
                showCombatMenu();
            } else {
                showRoomMenu();
            }
        }
    }

    private void showGameResult() {
        if (gameState.hasWon()) {
            output.println("Victory! You found the final treasure.");
        } else if (gameState.hasLost()) {
            output.println("Defeat! Your adventure ends here.");
        }
    }

    private void showRoomMenu() throws IOException {
        output.println();
        output.println(gameState.getCurrentRoom().getName());
        output.println(gameState.getCurrentRoom().getDescription());
        output.println("1. Move");
        output.println("2. Examine room");
        output.println("3. Solve puzzle");
        output.println("4. Request hint");
        output.println("5. View inventory");

        String choice = input.readLine();
        if (choice == null) {
            gameState.completeDefeat();
            return;
        }

        switch (choice.trim()) {
            case "1" -> move();
            case "2" -> showExits();
            case "3" -> solvePuzzle();
            case "4" -> requestHint();
            case "5" -> showInventory();
            default -> output.println("Invalid choice.");
        }
    }

    private void showCombatMenu() throws IOException {
        Combat combat = gameState.getCurrentCombat();
        output.println();
        output.println("Combat against " + combat.getEnemy().getName());
        output.println("1. Attack");
        output.println("2. Defend");
        output.println("3. Use potion");
        output.println("4. Flee");

        String choice = input.readLine();
        if (choice == null) {
            gameState.completeDefeat();
            return;
        }

        switch (choice.trim()) {
            case "1" -> combat.attack();
            case "2" -> combat.defend();
            case "3" -> {
                if (!combat.usePotion()) {
                    output.println("The potion cannot be used now.");
                }
            }
            case "4" -> combat.flee();
            default -> output.println("Invalid choice.");
        }

        gameState.resolveCombat();
    }

    private void move() throws IOException {
        output.print("Direction: ");
        String direction = input.readLine();
        if (!gameState.move(direction)) {
            output.println("You cannot move in that direction.");
        }
    }

    private void showExits() {
        output.println("Available exits: " + gameState.getCurrentRoom().getExits().keySet());
    }

    private void solvePuzzle() throws IOException {
        Puzzle puzzle = gameState.getCurrentRoom().getPuzzle();
        if (puzzle == null || puzzle.isSolved()) {
            output.println("There is no unsolved puzzle here.");
            return;
        }

        output.println(puzzle.getQuestion());
        output.print("Answer: ");
        String answer = input.readLine();
        if (!gameState.answerPuzzle(answer)) {
            output.println("That answer is incorrect.");
        } else {
            output.println("Correct. The way forward is open.");
        }
    }

    private void requestHint() {
        if (!gameState.useHint()) {
            output.println("There is no unsolved puzzle here.");
            return;
        }

        output.println(gameState.getCurrentRoom().getPuzzle().getHint());
    }

    private void showInventory() {
        output.println("Inventory: " + gameState.getPlayer().getInventory().getItems());
    }
}
