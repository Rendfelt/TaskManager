package org.dragard.projectmanager.util;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.transaction.TransactionScoped;

public class EntityManagerProducer {

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @TransactionScoped
    @Produces
    public EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
}
