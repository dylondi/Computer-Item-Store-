package com.server.side.inventory;

import java.io.IOException;

import java.util.List;
import java.util.Map;

import com.server.side.InMemDbGenerator;
import com.server.side.PersistenceApi;


public class CsvPersistenceApi implements PersistenceApi {
    private static String filepath;

    public CsvPersistenceApi(String filepath) {
        this.filepath = filepath;
    }

    @Override
    public Inventory load() throws IOException {

        Parser parser = new Parser(filepath);
        List<String> list = parser.readCSV();
        InventoryParser inventoryParser = new InventoryParser();
        InMemDbGenerator inMemDbGenerator = new InMemDbGenerator();
        return new Inventory(inMemDbGenerator.createInMemDb(inventoryParser.createObjects(list)));
    }

    @Override
    public void save(Map map) {

    }
}
