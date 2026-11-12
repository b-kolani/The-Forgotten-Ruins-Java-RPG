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

        if (currentRoom.isExitLocked(direction)) {
            return false;
        }

        Room destination = currentRoom.getExit(direction);
        if (destination == null) {
            return false;
        }

        currentRoom = destination;
        return true;
    }

    public boolean useHint() {
        return currentRoom.getPuzzle() != null
            && !currentRoom.getPuzzle().isSolved()
            && currentRoom.getPuzzle().requestHint() != null;
    }

    public boolean answerPuzzle(String answer) {
        Puzzle puzzle = currentRoom.getPuzzle();
        if (puzzle == null) {
            return false;
        }

        if (puzzle.submitAnswer(answer)) {
            currentRoom.unlockExit("north");
            return true;
        }

        if (puzzle.isTrapTriggered()) {
            player.takeDamage(puzzle.getTrapDamage());
            if (player.isAlive()) {
                puzzle.resetAfterTrap();
            } else {
                completeDefeat();
            }
        }

        return false;
    }

    private Room createStartingRoom() {
        Room entrance = new Room(
            "The Forgotten Entrance",
            "You stand at the entrance of an ancient dungeon."
        );
        Room ancientDoor = new Room(
            "The Door of Ancients",
            "A massive door covered in ancient inscriptions blocks the path."
        );
        ancientDoor.setPuzzle(new Puzzle(
            "I have keys but open no locks. What am I?",
            "keyboard",
            "You use me to type."
        ));
        Room crypt = new Room(
            "The Crypt",
            "Cold stone and forgotten coffins fill this dark chamber."
        );
        Room runeMechanism = new Room(
            "The Rune Mechanism",
            "A complex mechanism covered in glowing runes stands before you."
        );
        Room sanctuary = new Room(
            "The Final Sanctuary",
            "The final treasure rests in the heart of the sanctuary."
        );

        entrance.addExit("north", ancientDoor);
        ancientDoor.addExit("south", entrance);
        ancientDoor.addLockedExit("north", crypt);
        crypt.addExit("south", ancientDoor);
        crypt.addExit("east", runeMechanism);
        runeMechanism.addExit("west", crypt);
        runeMechanism.addExit("north", sanctuary);
        sanctuary.addExit("south", runeMechanism);

        return entrance;
    }
}
