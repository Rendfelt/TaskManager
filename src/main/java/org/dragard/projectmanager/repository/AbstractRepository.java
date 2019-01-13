package org.dragard.projectmanager.repository;

import org.dragard.projectmanager.Application;
import org.dragard.projectmanager.entity.AbstractEntity;
import org.dragard.projectmanager.exception.NoActiveElementException;

import java.util.*;

public abstract class AbstractRepository<E extends AbstractEntity>
    implements RepositoryInterface<E> {

    private final Map<Long, E> elements;
    private final Application application;
    private long maxId;
    private E active;

    @Override
    public void setActive(long id) {
        this.active = elements.get(id);
    }

    public E getActive() {
        return active;
    }

    public void setActive(E active) {
        this.active = active;
    }

    protected Map<Long, E> getElements() {
        return elements;
    }

    public void printElements() {
        for (E element :
                elements.values()) {
            System.out.println(element);
        }
    }

    protected Application getApplication() {
        return application;
    }

    protected long getNewId() {
        return ++maxId;
    }

    public AbstractRepository(Application application) {
        this.application = application;
        elements = new HashMap<>();
        maxId = 0;
    }

    @Override
    public E findById(long id) {
        return elements.get(id);
    }

    @Override
    public void update(String name, String description) throws NoActiveElementException {
        if (active == null){
            throw new NoActiveElementException("No active project");
        }
        create(active.getId(), name, description);
    }

    @Override
    public void clearActive() {
        active = null;
    }

    @Override
    public void delete() throws NoActiveElementException {
        if (active == null){
            throw new NoActiveElementException("No active element");
        }
        elements.remove(active.getId());
    }
}
