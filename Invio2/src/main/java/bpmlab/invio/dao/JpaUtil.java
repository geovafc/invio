/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bpmlab.invio.dao;

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
    private static ThreadLocal<EntityManager> manager = new ThreadLocal<EntityManager>();
    
    public static EntityManager getEntityManager() {
        if (factory == null) {
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        }
        if (manager.get() == null) {
            manager.set(factory.createEntityManager());
        }
        return manager.get();
    }

    public static void closeEntityManager() {
        if (manager.get().isOpen()) {
            manager.get().close();
            manager.set(null);
        }
    }
    
    public static void closeFactory() {
        closeEntityManager();
        factory.close();
    }
}
