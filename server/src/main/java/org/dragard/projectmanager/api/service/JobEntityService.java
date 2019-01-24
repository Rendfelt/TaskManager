package org.dragard.projectmanager.api.service;

import org.dragard.projectmanager.entity.AbstractJobEntity;

import java.util.Collection;

public interface JobEntityService<E extends AbstractJobEntity> extends EntityService<E>{

    Collection<E> getElementsByUserId(String id) throws Exception;

}
