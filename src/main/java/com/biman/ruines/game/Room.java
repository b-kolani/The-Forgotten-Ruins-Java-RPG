package com.biman.ruines.game;

import java.util.LinkedHashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.biman.ruines.entity.Enemy;

public class Room {

    private final String name;
    private final String description;
    private final Map<String, Room> exits = new LinkedHashMap<>();
    private final Set<String> lockedExits = new HashSet<>();
    private Puzzle puzzle;
    private Enemy enemy;

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void addExit(String direction, Room destination) {
        if (direction == null || direction.isBlank() || destination == null) {
            return;
        }

        exits.put(direction, destination);
    }

    public void addLockedExit(String direction, Room destination) {
        addExit(direction, destination);
        if (exits.containsKey(direction)) {
            lockedExits.add(direction);
        }
    }

    public boolean unlockExit(String direction) {
        return lockedExits.remove(direction);
    }

    public boolean isExitLocked(String direction) {
        return lockedExits.contains(direction);
    }

    public void setPuzzle(Puzzle puzzle) {
        this.puzzle = puzzle;
    }

    public Puzzle getPuzzle() {
        return puzzle;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public Room getExit(String direction) {
        return exits.get(direction);
    }

    public Map<String, Room> getExits() {
        return Map.copyOf(exits);
    }
}
