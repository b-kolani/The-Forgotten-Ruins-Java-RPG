package com.biman.ruines.game;

import java.util.LinkedHashMap;
import java.util.Map;

public class Room {

    private final String name;
    private final String description;
    private final Map<String, Room> exits = new LinkedHashMap<>();

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

    public Room getExit(String direction) {
        return exits.get(direction);
    }

    public Map<String, Room> getExits() {
        return Map.copyOf(exits);
    }
}
