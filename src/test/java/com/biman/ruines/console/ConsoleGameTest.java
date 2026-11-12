package com.biman.ruines.console;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;

public class ConsoleGameTest {

    @Test
    void consoleDisplaysWelcomeAndStartingRoom() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        ConsoleGame game = new ConsoleGame(
            new BufferedReader(new StringReader("")),
            new PrintStream(output, true, StandardCharsets.UTF_8)
        );

        game.run();

        String text = output.toString(StandardCharsets.UTF_8);
        assertTrue(text.contains("Welcome to The Forgotten Ruins."));
        assertTrue(text.contains("The Forgotten Entrance"));
    }

    @Test
    void invalidRoomChoiceIsReported() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        ConsoleGame game = new ConsoleGame(
            new BufferedReader(new StringReader("invalid\n")),
            new PrintStream(output, true, StandardCharsets.UTF_8)
        );

        game.run();

        assertTrue(output.toString(StandardCharsets.UTF_8).contains("Invalid choice."));
    }

    @Test
    void playerCanStartANewGameAfterInputEndsThePreviousGame() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        ConsoleGame game = new ConsoleGame(
            new BufferedReader(new StringReader(
                "1\nnorth\n"
                    + "3\nkeyboard\n"
                    + "1\nnorth\n"
                    + "1\n1\n1\n"
                    + "1\neast\n"
                    + "1\nnorth\n"
                    + "y\n"
            )),
            new PrintStream(output, true, StandardCharsets.UTF_8)
        );

        game.run();

        String text = output.toString(StandardCharsets.UTF_8);
        assertTrue(text.contains("Victory! You found the final treasure."));
        assertTrue(text.contains("Start a new game? (y/n)"));
        assertTrue(text.contains("The Forgotten Entrance"));
    }
}
