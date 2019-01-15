package org.dragard.projectmanager.api.repository;

import org.dragard.projectmanager.entity.AbstractEntity;

import java.util.Map;

public interface Repository<E extends AbstractEntity> {

    E getElementById(final String id);

    void create(E element);

    void update(E element);

    Map<String, E> getElements();

    void delete(String id);
}
