package com.server.side.orders;

import com.server.side.components.ComputerComponent;
import com.server.side.inventory.Inventory;


public class OrderManager {

    private static Inventory inventory;

    public OrderManager(Inventory inventory) {
        this.inventory = inventory;
    }

    public boolean checkStock(Order[] orders) {
        boolean flag = true;
        for (Order order : orders) {
            ComputerComponent computerComponent = inventory.searchInventoryById(order.getId());
            if (order.getQuantity() > computerComponent.getQuantity()) {
                System.out.println("Less or no stock for requested stock");
                flag = false;
            }
        }
        return flag;
    }

    public void updateInventory(Order[] orders) {
        for (Order order : orders) {
            ComputerComponent computerComponent = inventory.searchInventoryById(order.getId());
            computerComponent.setQuantity(computerComponent.getQuantity() - order.getQuantity());
            if (computerComponent.getQuantity() == 0) {
                inventory.getParsedInventory().remove(computerComponent.getUuid());
            }
        }
    }
}
