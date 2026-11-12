package com.biman.ruines.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.biman.ruines.entity.Enemy;
import com.biman.ruines.entity.Potion;
import com.biman.ruines.entity.Player;
import org.junit.jupiter.api.Test;

public class CombatTest {

    @Test
    void combatStartsWhenPlayerAndEnemyAreAlive() {
        Combat combat = new Combat(new Player(), new Enemy("Goblin", 40, 8));

        assertTrue(combat.isActive());
    }

    @Test
    void attackDamagesEnemyAndEnemyRipostes() {
        Player player = new Player();
        Enemy enemy = new Enemy("Goblin", 40, 8);
        Combat combat = new Combat(player, enemy);

        combat.attack();

        assertEquals(25, enemy.getHealth());
        assertEquals(92, player.getHealth());
        assertTrue(combat.isActive());
    }

    @Test
    void defeatingEnemyPreventsRiposteAndEndsCombat() {
        Player player = new Player();
        Enemy enemy = new Enemy("Goblin", 10, 8);
        Combat combat = new Combat(player, enemy);

        combat.attack();

        assertEquals(0, enemy.getHealth());
        assertEquals(100, player.getHealth());
        assertFalse(combat.isActive());
    }

    @Test
    void defeatedPlayerEndsCombat() {
        Player player = new Player();
        player.takeDamage(95);
        Enemy enemy = new Enemy("Goblin", 40, 8);
        Combat combat = new Combat(player, enemy);

        combat.attack();

        assertEquals(0, player.getHealth());
        assertFalse(combat.isActive());
        assertTrue(enemy.isAlive());
    }

    @Test
    void inactiveCombatIgnoresFurtherAttacks() {
        Player player = new Player();
        Enemy enemy = new Enemy("Goblin", 10, 8);
        Combat combat = new Combat(player, enemy);

        combat.attack();
        combat.attack();

        assertEquals(100, player.getHealth());
        assertEquals(0, enemy.getHealth());
    }

    @Test
    void defendingReducesTheEnemyAttackByHalf() {
        Player player = new Player();
        Enemy enemy = new Enemy("Goblin", 40, 9);
        Combat combat = new Combat(player, enemy);

        combat.defend();

        assertEquals(95, player.getHealth());
        assertTrue(combat.isActive());
    }

    @Test
    void defendingDoesNotDamageTheEnemy() {
        Player player = new Player();
        Enemy enemy = new Enemy("Goblin", 40, 8);
        Combat combat = new Combat(player, enemy);

        combat.defend();

        assertEquals(40, enemy.getHealth());
    }

    @Test
    void usingPotionHealsPlayerConsumesPotionAndEnemyRipostes() {
        Player player = new Player();
        player.takeDamage(30);
        Potion potion = new Potion("Healing Potion", "Restores health", 25);
        player.getInventory().addItem(potion);
        Enemy enemy = new Enemy("Goblin", 40, 8);
        Combat combat = new Combat(player, enemy);

        assertTrue(combat.usePotion());

        assertEquals(87, player.getHealth());
        assertFalse(player.getInventory().containsItem(potion));
        assertTrue(combat.isActive());
    }

    @Test
    void potionIsNotConsumedAtFullHealthAndEnemyDoesNotAttack() {
        Player player = new Player();
        Potion potion = new Potion("Healing Potion", "Restores health", 25);
        player.getInventory().addItem(potion);
        Enemy enemy = new Enemy("Goblin", 40, 8);
        Combat combat = new Combat(player, enemy);

        assertFalse(combat.usePotion());

        assertEquals(100, player.getHealth());
        assertTrue(player.getInventory().containsItem(potion));
        assertTrue(combat.isActive());
    }

    @Test
    void usingPotionWithoutPotionDoesNotAdvanceTurn() {
        Player player = new Player();
        Enemy enemy = new Enemy("Goblin", 40, 8);
        Combat combat = new Combat(player, enemy);

        assertFalse(combat.usePotion());

        assertEquals(100, player.getHealth());
        assertEquals(40, enemy.getHealth());
    }
}
