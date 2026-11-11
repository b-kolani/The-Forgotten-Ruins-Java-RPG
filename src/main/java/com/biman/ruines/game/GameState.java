package com.biman.ruines.game;

import com.biman.ruines.entity.Player;

public class GameState {

    private Player player;
    private Room currentRoom;
    private boolean active;
    private boolean won;
    private boolean lost;

    public GameState() {
        startNewGame();
    }

    public Player getPlayer() {
        return player;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public boolean isActive() {
        return active;
    }

    public boolean hasWon() {
        return won;
    }

    public boolean hasLost() {
        return lost;
    }

    public void completeVictory() {
        if (!active) {
            return;
        }

        active = false;
        won = true;
    }

    public void completeDefeat() {
        if (!active) {
            return;
        }

        active = false;
        lost = true;
    }

    public void startNewGame() {
        player = new Player();
        currentRoom = createStartingRoom();
        active = true;
        won = false;
        lost = false;
    }

    public boolean move(String direction) {
        if (!active || direction == null) {
            return false;
        }

        Room destination = currentRoom.getExit(direction);
        if (destination == null) {
            return false;
        }

        currentRoom = destination;
        return true;
    }

    private Room createStartingRoom() {
        return new Room(
            "The Forgotten Entrance",
            "You stand at the entrance of an ancient dungeon."
        );
    }
}
