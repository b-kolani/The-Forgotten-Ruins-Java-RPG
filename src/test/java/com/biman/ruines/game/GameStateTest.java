package com.biman.ruines.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.biman.ruines.entity.Player;
import org.junit.jupiter.api.Test;

public class GameStateTest {

    @Test
    void newGameStartsActiveWithFreshPlayer() {
        GameState gameState = new GameState();

        assertTrue(gameState.isActive());
        assertFalse(gameState.hasWon());
        assertFalse(gameState.hasLost());
        assertEquals(100, gameState.getPlayer().getHealth());
        assertTrue(gameState.getPlayer().getInventory().isEmpty());
        assertEquals("The Forgotten Entrance", gameState.getCurrentRoom().getName());
    }

    @Test
    void victoryEndsTheGame() {
        GameState gameState = new GameState();

        gameState.completeVictory();

        assertFalse(gameState.isActive());
        assertTrue(gameState.hasWon());
        assertFalse(gameState.hasLost());
    }

    @Test
    void defeatEndsTheGame() {
        GameState gameState = new GameState();

        gameState.completeDefeat();

        assertFalse(gameState.isActive());
        assertFalse(gameState.hasWon());
        assertTrue(gameState.hasLost());
    }

    @Test
    void completedGameCannotBeCompletedAgain() {
        GameState gameState = new GameState();

        gameState.completeVictory();
        gameState.completeDefeat();

        assertTrue(gameState.hasWon());
        assertFalse(gameState.hasLost());
    }

    @Test
    void newGameResetsThePreviousState() {
        GameState gameState = new GameState();
        gameState.getPlayer().takeDamage(50);
        gameState.completeVictory();
        Player previousPlayer = gameState.getPlayer();

        gameState.startNewGame();

        assertTrue(gameState.isActive());
        assertFalse(gameState.hasWon());
        assertFalse(gameState.hasLost());
        assertEquals(100, gameState.getPlayer().getHealth());
        assertTrue(gameState.getPlayer().getInventory().isEmpty());
        assertNotSame(previousPlayer, gameState.getPlayer());
        assertEquals("The Forgotten Entrance", gameState.getCurrentRoom().getName());
    }

    @Test
    void invalidMovementDoesNotChangeRoom() {
        GameState gameState = new GameState();
        Room startingRoom = gameState.getCurrentRoom();

        assertFalse(gameState.move("north"));
        assertEquals(startingRoom, gameState.getCurrentRoom());
    }

    @Test
    void movementIsRejectedAfterGameEnds() {
        GameState gameState = new GameState();

        gameState.completeVictory();

        assertFalse(gameState.move("north"));
    }
}
