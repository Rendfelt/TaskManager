package org.dragard.projectmanager.server.api.repository;

import org.dragard.projectmanager.server.entity.AbstractEntity;

import java.util.Collection;

public interface Repository<E extends AbstractEntity> {

    E getElementById(final String id);

    E merge(E element);

    void clearElements();

    Collection<E> getElements();

    E delete(String id);
}
