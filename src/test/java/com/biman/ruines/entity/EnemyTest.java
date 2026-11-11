package com.biman.ruines.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class EnemyTest {

    @Test 
    void enemyIsInitializedWithProvidedValues()
    {
        Enemy enemy = new Enemy("Gobelin", 40, 8);

        assertEquals("Gobelin", enemy.getName());

        assertEquals(40, enemy.getHealth());

        assertEquals(8, enemy.getAttackDamage());
    }

    @Test
    void enemyStartsAlive()
    {
        Enemy enemy = new Enemy("Gobelin", 40, 8);

        assertTrue(enemy.isAlive());
    }

    @Test
    void takingDamageReducesHealth()
    {
        Enemy enemy = new Enemy("Gobelin", 40, 8);

        enemy.takeDamage(15);

        assertEquals(25, enemy.getHealth());
    }

    @Test
    void damageCannotReduceHealthBelowZero()
    {
        Enemy enemy = new Enemy("Gobelin", 40, 8);

        enemy.takeDamage(50);

        assertEquals(0, enemy.getHealth());
        assertFalse(enemy.isAlive());
    }

    @Test
    void deadEnemyCannotTakeMoreDamage()
    {
        Enemy enemy = new Enemy("Gobelin", 40, 8);

        enemy.takeDamage(40);
        enemy.takeDamage(10);

        assertEquals(0, enemy.getHealth());
    }

    @Test
    void invalidDamageDoesNotChangeHealth()
    {
        Enemy enemy = new Enemy("Gobelin", 40, 8);

        enemy.takeDamage(-10);

        assertEquals(40, enemy.getHealth());
    }
    
}
