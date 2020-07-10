package com.server.side;

import java.io.IOException;

import java.util.Map;

import com.server.side.inventory.Inventory;


public interface PersistenceApi {

    Inventory load() throws IOException;

    void save(Map map);
}
