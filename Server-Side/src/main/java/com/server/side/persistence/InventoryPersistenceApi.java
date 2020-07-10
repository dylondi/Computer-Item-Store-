package com.server.side.persistence;

import java.util.Map;
import java.util.UUID;

import javax.persistence.EntityManager;

import com.server.side.InMemDbGenerator;
import com.server.side.PersistenceApi;
import com.server.side.components.ComputerComponent;
import com.server.side.inventory.Inventory;
import static com.server.side.persistence.Reader.getDataFromDB;


public class InventoryPersistenceApi implements PersistenceApi {

    private static EntityManager entityManager;

    public InventoryPersistenceApi(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Inventory load() {
        InMemDbGenerator inMemDbGenerator = new InMemDbGenerator();
        Map<UUID, ComputerComponent> map = inMemDbGenerator.createInMemDb(getDataFromDB(entityManager));
        return new Inventory(map);
    }

    @Override
    public void save(Map map) {
        Writer writer = new Writer();
        writer.writeListToDb(map, entityManager);
    }
}
