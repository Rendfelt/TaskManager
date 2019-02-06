package org.dragard.projectmanager.api.repository;

import org.dragard.projectmanager.entity.AbstractJobEntity;

import javax.persistence.EntityManager;
import java.util.List;

public interface JobRepository<E extends AbstractJobEntity> extends IRepository<E> {

    List<E> getElementsByUserId(String id, EntityManager entityManager) throws Exception;



}
