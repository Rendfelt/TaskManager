package org.dragard.projectmanager.repository_old;

import org.dragard.projectmanager.api.repository.Repository;
import org.dragard.projectmanager.entity.AbstractEntity;

import java.util.*;

public abstract class AbstractRepository<E extends AbstractEntity>
    implements Repository<E> {

    private final Map<String, E> elements;

    protected AbstractRepository() {
        elements = new HashMap<>();
    }

    @Override
    public void clearElements(){
        elements.clear();
    }

    @Override
    public Collection<E> getElements() throws Exception {
        return new ArrayList<>(elements.values());
    }

    @Override
    public E getElementById(String id) throws Exception {
        return elements.get(id);
    }

    @Override
    public E delete(String id) throws Exception {
        return elements.remove(id);
    }

    @Override
    public E merge(E element) throws Exception {
        elements.put(element.getId(), element);
        return element;
    }
}
