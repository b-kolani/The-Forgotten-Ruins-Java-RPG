package com.biman.ruines.game;

import com.biman.ruines.entity.Enemy;
import com.biman.ruines.entity.Item;
import com.biman.ruines.entity.Potion;
import com.biman.ruines.entity.Player;

public class Combat {

    private final Player player;
    private final Enemy enemy;
    private boolean active;
    private boolean fled;

    public Combat(Player player, Enemy enemy) {
        this.player = player;
        this.enemy = enemy;
        active = player != null
            && enemy != null
            && player.isAlive()
            && enemy.isAlive();
    }

    public Player getPlayer() {
        return player;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public boolean isActive() {
        return active;
    }

    public boolean hasFled() {
        return fled;
    }

    public void attack() {
        if (!active) {
            return;
        }

        enemy.takeDamage(player.getAttackDamage());
        if (!enemy.isAlive()) {
            active = false;
            return;
        }

        player.takeDamage(enemy.getAttackDamage());
        if (!player.isAlive()) {
            active = false;
        }
    }

    public void defend() {
        if (!active) {
            return;
        }

        int reducedDamage = (enemy.getAttackDamage() + 1) / 2;
        player.takeDamage(reducedDamage);
        if (!player.isAlive()) {
            active = false;
        }
    }

    public boolean usePotion() {
        if (!active) {
            return false;
        }

        Potion potion = findPotion();
        if (potion == null || player.heal(potion.getHealingAmount()) == 0) {
            return false;
        }

        player.getInventory().removeItem(potion);
        player.takeDamage(enemy.getAttackDamage());
        if (!player.isAlive()) {
            active = false;
        }
        return true;
    }

    public boolean flee() {
        if (!active) {
            return false;
        }

        active = false;
        fled = true;
        return true;
    }

    private Potion findPotion() {
        for (Item item : player.getInventory().getItems()) {
            if (item instanceof Potion potion) {
                return potion;
            }
        }
        return null;
    }
}
