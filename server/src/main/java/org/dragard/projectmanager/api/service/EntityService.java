package org.dragard.projectmanager.api.service;

import org.dragard.projectmanager.entity.AbstractEntity;

import java.util.Collection;

public interface EntityService<E extends AbstractEntity> extends Service{

    Collection<E> getElementsByUserId(String id) throws Exception;

    E delete(String id) throws Exception;

    Collection<E> getElements() throws Exception;

    void clearElements();

    E getElementById(String id) throws Exception;

    void persist(Collection<E> elements) throws Exception;
}
