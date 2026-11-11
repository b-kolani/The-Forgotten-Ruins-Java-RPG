package com.biman.ruines.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PlayerTest {
    
    @Test 
    void newPlayerStartsWithFullHealth()
    {
        Player player = new Player();

        assertEquals(100, player.getHealth());
    }

    @Test 
    void takeDamageReducesHealth() 
    {
        Player player = new Player();

        player.takeDamage(20);

        assertEquals(80, player.getHealth());
    }

    @Test 
    void negativeDamageDoesNotChangeHealth()
    {
        Player player = new Player();

        player.takeDamage(-20);

        assertEquals(100, player.getHealth());
    }

    @Test 
    void damageCannotReduceHealthBelowZero()
    {
        Player player = new Player();

        player.takeDamage(150);

        assertEquals(0, player.getHealth());

        assertFalse(player.isAlive());
    }

    @Test
    void deadPlayerCannotTakeMoreDamage()
    {
        Player player = new Player();

        player.takeDamage(100);

        assertEquals(0, player.getHealth());

        player.takeDamage(20);

        assertEquals(0, player.getHealth());
    }

    @Test
    void healRestoresHealth()
    {
        Player player = new Player();

        player.takeDamage(20);

        assertEquals(80, player.getHealth());

        player.heal(10);

        assertEquals(90, player.getHealth());
    }

    @Test
    void healCannotExceedMaxHealth()
    {
        Player player = new Player();

        player.takeDamage(10);

        player.heal(25);

        assertEquals(100, player.getHealth());
    }

    @Test 
    void healReturnsActualAmountHealed()
    {
        Player player = new Player();

        player.takeDamage(10);

        int amountHealed = player.heal(25);

        assertEquals(10, amountHealed);
    }

    @Test 
    void negativeHealingDoesNotChangeHealth()
    {
        Player player = new Player();

        player.takeDamage(20);

        int amountHealed = player.heal(-20);

        assertEquals(80, player.getHealth());

        assertEquals(0, amountHealed);
    }

    @Test 
    void healingAtMaxHealthReturnsZero()
    {
        Player player = new Player();

        int amountHealed = player.heal(25);

        assertEquals(100, player.getHealth());

        assertEquals(0, amountHealed);
    }

    @Test 
    void deadPlayerCannotBeRevivedByHealing()
    {
        Player player = new Player();

        player.takeDamage(100);

        int amountHealed = player.heal(25);

        assertEquals(0, player.getHealth());

        assertFalse(player.isAlive());

        assertEquals(0, amountHealed);
    }

    @Test 
    void newPlayerHasBaseAttackDamage()
    {
        Player player = new Player();

        assertEquals(15, player.getAttackDamage());
    }

    @Test
    void newPlayerHasEmptyInventory()
    {
        Player player = new Player();

        assertTrue(player.getInventory().isEmpty());
    }

    @Test
    void playerInventoryCanStoreItems()
    {
        Player player = new Player();
        Item item = new Item("Key", "Opens a door");

        player.getInventory().addItem(item);

        assertTrue(player.getInventory().containsItem(item));
    }
}
