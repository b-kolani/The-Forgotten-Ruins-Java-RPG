package com.biman.ruines.entity;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class InventoryTest {
    
    @Test 
    void newInventoryIsEmpty() {
        Inventory inventory = new Inventory();

        // Test if the new inventory is empty
        assertTrue(inventory.isEmpty());
    }

    @Test
    void addNewItemInInventory() {
        // Create an inventory
        Inventory inventory = new Inventory();
        // Create a new item 
        Item item = new Item("Potion", "Heals the player");

        // Add the item in the inventory
        inventory.addItem(item);

        // Test if the inventory is not empty
        assertFalse(inventory.isEmpty());
    }

    @Test 
    void containsAddedItem() {
        // Create a new Inventory
        Inventory inventory = new Inventory();

        // Create a new Item
        Item item = new Item("Key", "Open a door");

        // Add the item in the inventory
        inventory.addItem(item);

        // Check if the added item is present in the inventory
        assertTrue(inventory.containsItem(item));
    }

    @Test
    void doesNotContainItemThatWasNotAdded()
    {
        Inventory inventory = new Inventory();

        Item item = new Item("Key", "Open a door");

        assertFalse(inventory.containsItem(item));
    }

    @Test 
    void removedItemIsNoLongerPresent()
    {
        Inventory inventory = new Inventory();

        Item item = new Item("Potion", "Heal the player");

        inventory.addItem(item);

        assertTrue(inventory.containsItem(item));

        inventory.removeItem(item);

        assertFalse(inventory.containsItem(item));
    }

    @Test 
    void addingNullDoesNotChangeTheInventory()
    {
        Inventory inventory = new Inventory();

        assertTrue(inventory.isEmpty());

        inventory.addItem(null);

        assertTrue(inventory.isEmpty());
    }

    @Test 
    void removingNullDoesNotChangeTheInventory()
    {
        Inventory inventory = new Inventory();

        Item item = new Item("Key", "Open door");

        inventory.addItem(item);

        assertFalse(inventory.isEmpty());

        inventory.removeItem(null);

        assertTrue(inventory.containsItem(item));
    }

    @Test 
    void getItemsReturnsUnmodifiableList()
    {
        Inventory inventory = new Inventory();

        Item item = new Item("Key", "Open door");

        inventory.addItem(item);

        assertThrows(UnsupportedOperationException.class, 
            () -> inventory.getItems().clear());

        assertTrue(inventory.containsItem(item));
    }
}
