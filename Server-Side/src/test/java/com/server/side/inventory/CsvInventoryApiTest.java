package com.server.side.inventory;

import java.io.IOException;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.server.side.InMemDbGenerator;
import com.server.side.PersistenceApi;
import com.server.side.components.ComputerComponent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class CsvInventoryApiTest {

    static Parser parser;
    static InventoryParser inventoryParser;
    static List<ComputerComponent> list;
    static Inventory inventory;
    static Map<UUID, ComputerComponent> expectedResult;

    @BeforeAll
    public static void setUp() throws IOException {
        parser = new Parser("src/test/resources/test_csv.csv");
        list = inventoryParser.createObjects(parser.readCSV());
        InMemDbGenerator inMemDbGenerator = new InMemDbGenerator();
        expectedResult = inMemDbGenerator.createInMemDb(list);
    }

    @Test
    void test_load_method() throws IOException {
        PersistenceApi persistenceApi = new CsvPersistenceApi("src/test/resources/test_csv.csv");
        Inventory actualResInv = persistenceApi.load();
        Map<UUID, ComputerComponent> actualResult = actualResInv.getParsedInventory();
        assertEquals(expectedResult, actualResult);
    }

}
