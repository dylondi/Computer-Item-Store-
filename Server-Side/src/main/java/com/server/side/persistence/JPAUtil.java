package com.server.side.persistence;

import javax.persistence.EntityManagerFactory;


public class JPAUtil {

    private static EntityManagerFactory factory;

    public static void shutdown() {
        if (factory != null) {
            factory.close();
        }
    }
}
