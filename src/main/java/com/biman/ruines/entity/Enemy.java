package com.biman.ruines.entity;

public class Enemy {
    private String name;

    private int health;

    private int attackDamage;

    public Enemy(String name, int health, int attackDamage)
    {
        this.name = name;
        this.health = health;
        this.attackDamage = attackDamage;
    }

    public String getName()
    {
        return name;
    }

    public int getHealth()
    {
        return health;
    }

    public int getAttackDamage()
    {
        return attackDamage;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void takeDamage(int damage) {
        if (damage <= 0 || !isAlive()) {
            return;
        }

        health -= Math.min(damage, health);
    }
}
