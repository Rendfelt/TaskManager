package org.dragard.projectmanager.api.repository;

import org.dragard.projectmanager.entity.AbstractEntity;

import java.util.Collection;

public interface Repository<E extends AbstractEntity> {

    E getElementById(final String id) throws Exception;

    E merge(E element) throws Exception;

    void clearElements();

    Collection<E> getElements() throws Exception;

    Collection<E> getElementsByUserId(String id) throws Exception;

    E delete(String id) throws Exception;
}
