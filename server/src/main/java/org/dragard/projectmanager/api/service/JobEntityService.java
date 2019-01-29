package org.dragard.projectmanager.api.service;

import org.dragard.projectmanager.entity.AbstractJobEntity;

import java.util.List;

public interface JobEntityService<E extends AbstractJobEntity> extends EntityService<E>{

    List<E> getElementsByUserId(String userId) throws Exception;

}
