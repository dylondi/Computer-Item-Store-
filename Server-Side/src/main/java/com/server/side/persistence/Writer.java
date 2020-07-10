package com.server.side.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.persistence.EntityManager;

import com.server.side.components.ComputerComponent;


public class Writer {

    public static void writeListToDb(Map<UUID, ComputerComponent> map, EntityManager entityManager) {
        List<ComputerComponent> computerComponent = new ArrayList<>(map.values());
        for (ComputerComponent item : computerComponent) {
            writeToDb(item, entityManager);
        }
    }

    static void writeToDb(ComputerComponent obj, EntityManager entityManager) {
        entityManager.getTransaction().begin();
        entityManager.persist(obj);
        entityManager.getTransaction().commit();
    }
}
