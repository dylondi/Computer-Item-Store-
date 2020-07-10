package com.server.side.inventory;

import java.util.ArrayList;
import java.util.List;

import com.server.side.components.ComputerComponent;
import com.server.side.components.ComputerComponentFactory;


public class InventoryParser {

    public static List<ComputerComponent> createObjects(List<String> parsedCSV) {
        List<ComputerComponent> listOfObjects = new ArrayList<>();
        for (int i = 1; i < parsedCSV.size(); i++) {
            String[] csvList = (parsedCSV.get(i)).replaceAll("\\s", "").split(",");
            ComputerComponent cc = ComputerComponentFactory.createComputerComponent(csvList);
            listOfObjects.add(cc);

        }
        return listOfObjects;
    }
}
