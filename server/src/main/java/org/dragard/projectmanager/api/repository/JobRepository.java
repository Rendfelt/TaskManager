package org.dragard.projectmanager.api.repository;

import org.dragard.projectmanager.entity.AbstractJobEntity;

import java.util.List;

public interface JobRepository<E extends AbstractJobEntity> extends IRepository<E> {

    List<E> findByUser_id(String id) throws Exception;

}
