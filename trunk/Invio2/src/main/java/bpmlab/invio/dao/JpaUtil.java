/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bpmlab.invio.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author bpmlab
 */
public class JpaUtil {

    private static final String PERSISTENCE_UNIT = "bpmlab_Invio2_war_1.0-SNAPSHOTPU";
    private static EntityManagerFactory factory;
    private static EntityManager manager;

    public static EntityManager getEntityManager() {
        if (factory == null) {
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        }
        if (manager == null || !manager.isOpen()) {
            manager = factory.createEntityManager();
        }
        return manager;
    }

    public static void closeEntityManager() {
        if (manager.isOpen()) {
            manager.close();
        }
    }

    public static void closeFactory() {
        if (factory != null || factory.isOpen()) {
            factory.close();
        }
    }
}
