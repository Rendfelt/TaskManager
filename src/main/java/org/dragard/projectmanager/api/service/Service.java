package org.dragard.projectmanager.api.service;

import org.dragard.projectmanager.entity.AbstractEntity;
import org.dragard.projectmanager.exception.NoElementWithIdException;

import java.util.Map;

public interface Service<E extends AbstractEntity> {

    void delete(String id) throws NoElementWithIdException;

    Map<String, E> getElements();

    E getElementById(final String id);
}
