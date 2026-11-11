package com.biman.ruines.entity;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
 
    private List<Item> items = new ArrayList<>();

    public void addItem(Item item) {
        if (item == null) {
            return;
        }

        items.add(item);
    }

    public void removeItem(Item item) {
        if (item == null) {
            return;
        }

        items.remove(item);
    }

    public boolean containsItem(Item item) {
        return item != null && items.contains(item);
    }

    public List<Item> getItems() {
        return List.copyOf(items);
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}
