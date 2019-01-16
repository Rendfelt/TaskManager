package org.dragard.projectmanager.repository;

import org.dragard.projectmanager.api.repository.Repository;
import org.dragard.projectmanager.entity.AbstractEntity;

import java.util.*;

public abstract class AbstractRepository<E extends AbstractEntity>
    implements Repository<E> {

    private final Map<String, E> elements;

    public Map<String, E> getElements() {
        return elements;
    }

    protected AbstractRepository() {
        elements = new HashMap<>();
    }

    @Override
    public E getElementById(String id) {
        return elements.get(id);
    }

    @Override
    public void delete(String id) {
        elements.remove(id);
    }

    @Override
    public void create(E element) {
        elements.put(element.getId(), element);
    }

    @Override
    public void update(E element) {
        create(element);
    }
}
