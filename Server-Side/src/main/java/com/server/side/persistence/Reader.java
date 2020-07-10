package com.server.side.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import com.server.side.components.ComputerComponent;


public class Reader {

    public static List<ComputerComponent> getDataFromDB(EntityManager entityManager) {

        entityManager.getTransaction().begin();
        List<ComputerComponent> result = entityManager.createQuery("from ComputerComponent ", ComputerComponent.class).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return result;
    }

    public static ComputerComponent findComponent(Map<UUID, ComputerComponent> map, String name) {
        List<ComputerComponent> computerComponents = new ArrayList<>(map.values());
        for (ComputerComponent computerComponent : computerComponents) {
            if (computerComponent.getName().equals(name)) {
                return computerComponent;
            }
        }
        return null;
    }

    public static int getNumOfItems(Map<UUID, ComputerComponent> map) {
        List<ComputerComponent> computerComponents = new ArrayList<>(map.values());
        int totalNumOfItems = computerComponents.stream().collect(Collectors.summingInt(ComputerComponent::getQuantity));
        return totalNumOfItems;
    }
}
