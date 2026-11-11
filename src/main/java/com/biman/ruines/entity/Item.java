package com.biman.ruines.entity;

import java.util.UUID;

public class Item {
    
    private UUID id;
    private String name;
    private String description;

    public Item(String name, String description) {
        id = UUID.randomUUID();
        this.name = name;
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
