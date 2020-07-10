package com.server.side.inventory;

import java.util.Map;
import java.util.UUID;

import com.server.side.components.ComputerComponent;


public class Inventory {

    private static Map<UUID, ComputerComponent> parsedInventory;

    public Inventory(Map<UUID, ComputerComponent> parsedInventory) {
        this.parsedInventory = parsedInventory;
    }

    public Map<UUID, ComputerComponent> getParsedInventory() {
        return parsedInventory;
    }

    public ComputerComponent searchInventoryById(UUID uuid) {
        return this.parsedInventory.get(uuid);
    }
}
