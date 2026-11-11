package com.biman.ruines.entity;

// Player class
public class Player {
    
    private int health;
    private int maxHealth;
    private int attackDamage;
    private Inventory inventory;

    public Player() {
        health = 100;
        maxHealth = 100;
        attackDamage = 15;
        inventory = new Inventory();
    }

    public int getHealth()
    {
        return health;
    }

    public int getAttackDamage()
    {
        return attackDamage;
    }

    public Inventory getInventory() {
        return inventory;
    }
    
    public boolean isAlive() {
        return health > 0;
    }

    public void takeDamage(int damage) {
        if (damage <= 0 || !isAlive()) {
            return;
        }

        damage = Math.min(damage, health);
        health -= damage;
    }

    public int heal(int amount) {
        if (!isAlive() || amount <= 0) {
            return 0;
        }

        amount = Math.min(amount, maxHealth - health);

        health += amount;
        return amount;
    }

}
