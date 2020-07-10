package com.server.side;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.server.side.components.ComputerComponent;


public class InMemDbGenerator {

    public Map<UUID, ComputerComponent> createInMemDb(List<ComputerComponent> list) {
        Map<UUID, ComputerComponent> map = new HashMap<>();
        for (ComputerComponent computerComponent : list) {
            map.put(computerComponent.getUuid(), computerComponent);
        }
        return map;
    }
}
