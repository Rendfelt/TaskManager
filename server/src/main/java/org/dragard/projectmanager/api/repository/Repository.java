package org.dragard.projectmanager.api.repository;

import org.dragard.projectmanager.entity.AbstractEntity;

import javax.persistence.EntityManager;
import java.util.Collection;

public interface Repository<E extends AbstractEntity> {

    E getElementById(String id, EntityManager entityManager) throws Exception;

    E merge(E element, EntityManager entityManager) throws Exception;

    E delete(String id, EntityManager entityManager) throws Exception;
}
