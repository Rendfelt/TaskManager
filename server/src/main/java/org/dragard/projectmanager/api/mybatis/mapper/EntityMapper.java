package org.dragard.projectmanager.api.mybatis.mapper;

import org.dragard.projectmanager.entity.AbstractEntity;

import java.util.Collection;

public interface EntityMapper<E extends AbstractEntity> {


    void insert(E element) throws Exception;

    void update(E element) throws Exception;

    Collection<E> getAll() throws Exception;

    E getById(String id);

    void delete(String id) throws Exception;
}
