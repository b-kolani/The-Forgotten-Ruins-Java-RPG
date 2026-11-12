package com.biman.ruines.entity;

public class Potion extends Item {

    private final int healingAmount;

    public Potion(String name, String description, int healingAmount) {
        super(name, description);
        this.healingAmount = Math.max(0, healingAmount);
    }

    public int getHealingAmount() {
        return healingAmount;
    }
}
