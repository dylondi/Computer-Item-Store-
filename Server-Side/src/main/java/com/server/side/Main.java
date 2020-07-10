package com.server.side;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.server.side.inventory.CsvPersistenceApi;
import com.server.side.inventory.Inventory;
import com.server.side.persistence.InventoryPersistenceApi;
import com.server.side.persistence.JPAUtil;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    private static final String FILEPATH = "src/main/resources/Inventory.csv";
    static EntityManagerFactory factory;
    static EntityManager entityManager;
    public static Inventory inventory;

    public static void main(String[] args) throws IOException {
        SpringApplication.run(Main.class, args);
        factory = Persistence.createEntityManagerFactory("ComputerComponents");
        entityManager = factory.createEntityManager();
        PersistenceApi csvPersistenceApi = new CsvPersistenceApi(FILEPATH);
        inventory = csvPersistenceApi.load();

        Analyser analyser = new Analyser();
        // analyser.performAnalysis(inventory);

        PersistenceApi inventoryPersistenceApi = new InventoryPersistenceApi(entityManager);
        inventoryPersistenceApi.save(inventory.getParsedInventory());

        Inventory inventoryLoadedFromDB = inventoryPersistenceApi.load();

        analyser.performAnalysis(inventoryLoadedFromDB);
        entityManager.close();
        JPAUtil.shutdown();

    }

}
