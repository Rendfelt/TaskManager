package org.dragard.projectmanager.api.service;

import org.dragard.projectmanager.entity.AbstractEntity;

import java.util.Collection;

public interface EntityService<E extends AbstractEntity> extends Service{

    void delete(String id);

    Collection<E> getElements();

    void clearElements();

    E getElementById(String id);

    void persist(Collection<E> elements);
}