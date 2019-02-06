package org.dragard.projectmanager.util;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class EntityManagerProducer {

    @PersistenceContext(unitName = "primary")
    private EntityManager entityManager;

    @ApplicationScoped
    @Produces
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
