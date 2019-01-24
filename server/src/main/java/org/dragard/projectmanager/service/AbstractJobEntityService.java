package org.dragard.projectmanager.service;

import org.dragard.projectmanager.api.repository.JobRepository;
import org.dragard.projectmanager.api.repository.Repository;
import org.dragard.projectmanager.api.service.JobEntityService;
import org.dragard.projectmanager.entity.AbstractJobEntity;

import java.util.Collection;

public abstract class AbstractJobEntityService<E extends AbstractJobEntity> extends AbstractEntityService<E>
    implements JobEntityService<E> {

    private final JobRepository<E> repository;

    protected AbstractJobEntityService(JobRepository<E> repository) {
        this.repository = repository;
    }

    protected JobRepository<E> getRepository() {
        return repository;
    }

    @Override
    public Collection<E> getElementsByUserId(String id) throws Exception {
        return getRepository().getElementsByUserId(id);
    }
}
