package org.dragard.projectmanager.api.mybatis.mapper;

import org.dragard.projectmanager.entity.AbstractJobEntity;

import java.util.Collection;

public interface JobEntityMapper<E extends AbstractJobEntity> extends EntityMapper<E> {

    Collection<E> getByUserId(String id) throws Exception;
}
