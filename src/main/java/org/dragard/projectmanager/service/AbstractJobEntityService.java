package org.dragard.projectmanager.service;

import org.dragard.projectmanager.api.repository.Repository;
import org.dragard.projectmanager.entity.AbstractEntity;

public abstract class AbstractJobEntityService<E extends AbstractEntity> extends AbstractEntityService<E> {

    private final Repository<E> repository;

    protected AbstractJobEntityService(Repository<E> repository) {
        this.repository = repository;
    }

    protected Repository<E> getRepository() {
        return repository;
    }
}
