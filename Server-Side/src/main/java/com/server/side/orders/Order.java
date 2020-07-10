package com.server.side.orders;

import java.util.UUID;


public class Order {

    private final UUID id;
    private final String category;
    private final int price;
    private final int quantity;

    public Order(UUID id, String category, int price, int quantity) {
        this.id = id;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }

    public UUID getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}
