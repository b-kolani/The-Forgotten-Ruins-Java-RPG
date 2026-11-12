package com.biman.ruines.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PotionTest {

    @Test
    void potionStoresItsHealingAmount() {
        Potion potion = new Potion("Healing Potion", "Restores health", 25);

        assertEquals(25, potion.getHealingAmount());
    }

    @Test
    void negativeHealingAmountBecomesZero() {
        Potion potion = new Potion("Healing Potion", "Restores health", -10);

        assertEquals(0, potion.getHealingAmount());
    }
}
