package com.server.side.persistence;

import java.io.IOException;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.server.side.PersistenceApi;
import com.server.side.components.CPU;
import com.server.side.components.ComputerComponent;
import com.server.side.inventory.CsvPersistenceApi;
import com.server.side.inventory.Inventory;
import com.server.side.inventory.InventoryParser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class ReaderTest {

    static Writer writer;
    static Reader reader;
    static EntityManager entityManager;
    static EntityManagerFactory factory;
    static InventoryParser inventoryParser;
    static Inventory inventory;

    @BeforeAll
    public static void setUp() throws IOException {

        PersistenceApi csvPersistenceApi = new CsvPersistenceApi("src/main/resources/Inventory.csv");
        inventory = csvPersistenceApi.load();
        factory = Persistence.createEntityManagerFactory("ComputerComponents");
        inventoryParser = new InventoryParser();
        entityManager = factory.createEntityManager();
        writer = new Writer();
        writer.writeListToDb(inventory.getParsedInventory(), entityManager);
        reader = new Reader();
    }

    @Test
    void test_reader_class() {
        List<ComputerComponent> list = reader.getDataFromDB(entityManager);
        ComputerComponent cc = list.get(0);
        assertEquals(CPU.class, cc.getClass());
    }
}
