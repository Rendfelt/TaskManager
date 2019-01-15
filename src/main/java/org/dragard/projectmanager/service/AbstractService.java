package org.dragard.projectmanager.service;

import org.dragard.projectmanager.api.repository.Repository;
import org.dragard.projectmanager.api.service.Service;
import org.dragard.projectmanager.entity.AbstractEntity;
import org.dragard.projectmanager.exception.NoElementWithIdException;

import java.util.Map;

public abstract class AbstractService<E extends AbstractEntity>
    implements Service<E> {

    private final Repository<E> repository;

    protected AbstractService(Repository<E> repository) {
        this.repository = repository;
    }

    protected Repository<E> getRepository() {
        return repository;
    }

    @Override
    public E getElementById(String id) {
        return getRepository().getElementById(id);
    }

    @Override
    public Map<String, E> getElements() {
        return getRepository().getElements();
    }

    @Override
    public void delete(String id) throws NoElementWithIdException {
        if (getRepository().getElementById(id) == null){
            throw new NoElementWithIdException();
        }
        getRepository().delete(id);
    }
}
