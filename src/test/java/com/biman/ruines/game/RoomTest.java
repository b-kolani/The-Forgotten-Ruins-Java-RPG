package com.biman.ruines.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class RoomTest {

    @Test
    void roomStoresItsNameAndDescription() {
        Room room = new Room("Entrance", "An ancient entrance.");

        assertEquals("Entrance", room.getName());
        assertEquals("An ancient entrance.", room.getDescription());
    }

    @Test
    void roomCanAddAndUseAnExit() {
        Room entrance = new Room("Entrance", "An entrance.");
        Room crypt = new Room("Crypt", "A dark crypt.");

        entrance.addExit("north", crypt);

        assertEquals(crypt, entrance.getExit("north"));
        assertTrue(entrance.getExits().containsKey("north"));
    }

    @Test
    void unknownExitReturnsNull() {
        Room entrance = new Room("Entrance", "An entrance.");

        assertNull(entrance.getExit("west"));
    }

    @Test
    void invalidExitIsIgnored() {
        Room entrance = new Room("Entrance", "An entrance.");

        entrance.addExit(null, entrance);
        entrance.addExit(" ", entrance);
        entrance.addExit("north", null);

        assertTrue(entrance.getExits().isEmpty());
    }

    @Test
    void exitsCannotBeModifiedThroughReturnedMap() {
        Room entrance = new Room("Entrance", "An entrance.");
        Room crypt = new Room("Crypt", "A dark crypt.");
        entrance.addExit("north", crypt);

        assertFalse(entrance.getExits().isEmpty());
        assertThrows(UnsupportedOperationException.class, () -> entrance.getExits().clear());
        assertEquals(crypt, entrance.getExit("north"));
    }
}
