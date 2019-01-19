package org.dragard.projectmanager.server.service;

import org.dragard.projectmanager.server.api.repository.Repository;
import org.dragard.projectmanager.server.entity.AbstractJobEntity;

public abstract class AbstractJobEntityService<E extends AbstractJobEntity> extends AbstractEntityService<E> {

    private final Repository<E> repository;

    protected AbstractJobEntityService(Repository<E> repository) {
        this.repository = repository;
    }

    protected Repository<E> getRepository() {
        return repository;
    }
}
