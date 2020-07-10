package com.server.side.components;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;


@Entity
public class Mouse extends ComputerComponent {

    @Column(name = "DIMENSION")
    private String dimension;
    @Column(name = "COLOUR")
    private String colour;

    public Mouse() {
    }

    public Mouse(Mouse.MouseBuilder mouseBuilder) {
        super(mouseBuilder);
        this.dimension = mouseBuilder.dimension;
        this.colour = mouseBuilder.colour;

    }

    public String getDimension() {
        return dimension;
    }

    public String getColour() {
        return colour;
    }

    @Override
    public String toString() {
        return String.format("%s\t%s\t%s\t%s\t%s\t%s\t%d\t%d\n", getUuid(), getCategory(), getName(), getBrand(), getDimension(), getColour(), getPrice(), getQuantity());
    }

    public static class MouseBuilder extends ComputerComponent.Builder {
        private String dimension;
        private String colour;

        public MouseBuilder() {
            super();

        }

        public Mouse build() {
            return new Mouse(this);
        }

        public Mouse.MouseBuilder uuid(UUID uuid) {
            super.uuid = uuid;
            return this;
        }

        public MouseBuilder category(String category) {
            super.category = category;
            return this;
        }

        public MouseBuilder name(String name) {
            super.name = name;
            return this;
        }

        public MouseBuilder brand(String brand) {
            super.brand = brand;
            return this;
        }

        public MouseBuilder price(int price) {
            super.price = price;
            return this;
        }

        public MouseBuilder quantity(int quantity) {
            super.quantity = quantity;
            return this;
        }

        public MouseBuilder dimension(String dimension) {
            this.dimension = dimension;
            return this;
        }

        public MouseBuilder colour(String colour) {
            this.colour = colour;
            return this;
        }

    }

}
