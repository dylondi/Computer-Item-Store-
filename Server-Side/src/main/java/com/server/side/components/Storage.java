package com.server.side.components;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;


@Entity
public class Storage extends ComputerComponent {

    @Column(name = "DIMENSION")
    private String dimension;
    @Column(name = "INTERFACE")
    private String intrfce;
    @Column(name = "SIZE")
    private String size;

    public Storage() {
    }

    public Storage(Storage.StorageBuilder memoryBuilder) {
        super(memoryBuilder);
        this.dimension = memoryBuilder.dimension;
        this.intrfce = memoryBuilder.intrfce;
        this.size = memoryBuilder.size;

    }

    public String getIntrfce() {
        return intrfce;
    }

    public String getSize() {
        return size;
    }

    public String getDimension() {
        return dimension;
    }

    @Override
    public String toString() {
        return String.format("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%d\t%d\n", getUuid(), getCategory(), getName(), getBrand(), getDimension(), getIntrfce(), getSize(), getPrice(), getQuantity());
    }

    public static class StorageBuilder extends ComputerComponent.Builder {
        private String dimension;
        private String intrfce;
        private String size;

        public StorageBuilder() {
            super();
        }

        public Storage build() {
            return new Storage(this);
        }

        public Storage.StorageBuilder uuid(UUID uuid) {
            super.uuid = uuid;
            return this;
        }

        public StorageBuilder category(String category) {
            super.category = category;
            return this;
        }

        public StorageBuilder name(String name) {
            super.name = name;
            return this;
        }

        public StorageBuilder brand(String brand) {
            super.brand = brand;
            return this;
        }

        public StorageBuilder price(int price) {
            super.price = price;
            return this;
        }

        public StorageBuilder quantity(int quantity) {
            super.quantity = quantity;
            return this;
        }

        public StorageBuilder dimension(String dimension) {
            this.dimension = dimension;
            return this;
        }

        public StorageBuilder intrfce(String intrfce) {
            this.intrfce = intrfce;
            return this;
        }

        public StorageBuilder size(String size) {
            this.size = size;
            return this;
        }

    }

}
