package com.server.side.persistence;

import java.io.IOException;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.server.side.InMemDbGenerator;
import com.server.side.PersistenceApi;
import com.server.side.components.ComputerComponent;
import com.server.side.inventory.Inventory;
import com.server.side.inventory.InventoryParser;
import com.server.side.inventory.Parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class InventoryPersistenceApiTest {

    static Parser parser;
    static InventoryParser inventoryParser;
    static List<ComputerComponent> list;
    static Inventory inventory;
    static EntityManagerFactory factory;
    static EntityManager entityManager;
    static Map<UUID, ComputerComponent> expectedResult;

    @BeforeAll
    public static void setUp() throws IOException {
        parser = new Parser("src/test/resources/test_csv.csv");
        list = inventoryParser.createObjects(parser.readCSV());
        InMemDbGenerator inMemDbGenerator = new InMemDbGenerator();
        expectedResult = inMemDbGenerator.createInMemDb(list);
        factory = Persistence.createEntityManagerFactory("ComputerComponents");
        entityManager = factory.createEntityManager();
        inventory = new Inventory(expectedResult);
    }

    @Test
    void test_save_and_load_methods() throws IOException {
        PersistenceApi inventoryPersistenceApi = new InventoryPersistenceApi(entityManager);
        inventoryPersistenceApi.save(inventory.getParsedInventory());
        Inventory inventory2 = inventoryPersistenceApi.load();
        Map<UUID, ComputerComponent> actualResult = inventory2.getParsedInventory();
        assertEquals(expectedResult, actualResult);

    }
}
