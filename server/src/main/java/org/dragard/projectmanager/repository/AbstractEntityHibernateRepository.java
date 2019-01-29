package org.dragard.projectmanager.repository;

import lombok.AccessLevel;
import lombok.Getter;
import org.dragard.projectmanager.api.repository.Repository;
import org.dragard.projectmanager.entity.AbstractEntity;

import javax.persistence.EntityManager;

public abstract class AbstractEntityHibernateRepository<E extends AbstractEntity>
    implements Repository<E>{

    @Getter(AccessLevel.PROTECTED)
    private Class<E> clazz;

    public AbstractEntityHibernateRepository(Class<E> clazz) {
        this.clazz = clazz;
    }

    @Override
    public E getElementById(String id, EntityManager entityManager) throws Exception {
        return entityManager.find(clazz, id);
    }

    @Override
    public E merge(E element, EntityManager entityManager) throws Exception {
        return entityManager.merge(element);
    }

    @Override
    public E delete(String id, EntityManager entityManager) throws Exception {
        E element = getElementById(id, entityManager);
        entityManager.remove(element);
        return element;
    }
}
