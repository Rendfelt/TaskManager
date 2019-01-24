package org.dragard.projectmanager.api.repository;

import org.dragard.projectmanager.entity.AbstractJobEntity;

import java.util.Collection;

public interface JobRepository<E extends AbstractJobEntity> extends Repository<E> {

    Collection<E> getElementsByUserId(String id) throws Exception;
}
