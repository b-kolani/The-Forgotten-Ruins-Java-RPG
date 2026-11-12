package com.biman.ruines.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PuzzleTest {

    @Test
    void puzzleStartsWithThreeAttempts() {
        Puzzle puzzle = new Puzzle("Question", "answer", "Hint");

        assertEquals(3, puzzle.getAttemptsRemaining());
        assertFalse(puzzle.isSolved());
    }

    @Test
    void incorrectAnswerConsumesOneAttempt() {
        Puzzle puzzle = new Puzzle("Question", "answer", "Hint");

        assertFalse(puzzle.submitAnswer("wrong"));

        assertEquals(2, puzzle.getAttemptsRemaining());
    }

    @Test
    void correctAnswerSolvesPuzzleWithoutConsumingAttempt() {
        Puzzle puzzle = new Puzzle("Question", "answer", "Hint");

        assertTrue(puzzle.submitAnswer(" ANSWER "));

        assertTrue(puzzle.isSolved());
        assertEquals(3, puzzle.getAttemptsRemaining());
    }

    @Test
    void hintDoesNotConsumeAttempt() {
        Puzzle puzzle = new Puzzle("Question", "answer", "Hint");

        assertEquals("Hint", puzzle.requestHint());
        assertTrue(puzzle.isHintUsed());
        assertEquals(3, puzzle.getAttemptsRemaining());
    }

    @Test
    void exhaustedPuzzleCannotBeAnswered() {
        Puzzle puzzle = new Puzzle("Question", "answer", "Hint");

        puzzle.submitAnswer("wrong");
        puzzle.submitAnswer("wrong");
        puzzle.submitAnswer("wrong");

        assertEquals(0, puzzle.getAttemptsRemaining());
        assertFalse(puzzle.submitAnswer("answer"));
        assertFalse(puzzle.isSolved());
    }

    @Test
    void thirdIncorrectAnswerTriggersTrap() {
        Puzzle puzzle = new Puzzle("Question", "answer", "Hint", 20);

        puzzle.submitAnswer("wrong");
        puzzle.submitAnswer("wrong");
        puzzle.submitAnswer("wrong");

        assertTrue(puzzle.isTrapTriggered());
        assertEquals(20, puzzle.getTrapDamage());
    }

    @Test
    void resettingAfterTrapStartsANewAttemptCycle() {
        Puzzle puzzle = new Puzzle("Question", "answer", "Hint");

        puzzle.submitAnswer("wrong");
        puzzle.submitAnswer("wrong");
        puzzle.submitAnswer("wrong");
        puzzle.resetAfterTrap();

        assertFalse(puzzle.isTrapTriggered());
        assertEquals(3, puzzle.getAttemptsRemaining());
    }
}
