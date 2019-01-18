package org.dragard.projectmanager.api.repository;

import org.dragard.projectmanager.entity.AbstractEntity;

import java.util.Collection;

public interface Repository<E extends AbstractEntity> {

    E getElementById(final String id);

    void merge(E element);

    void clearElements();

    Collection<E> getElements();

    void delete(String id);
}
