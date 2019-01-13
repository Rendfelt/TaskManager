package org.dragard.projectmanager.repository;

import org.dragard.projectmanager.entity.AbstractEntity;
import org.dragard.projectmanager.exception.NoActiveElementException;

public interface RepositoryInterface<E extends AbstractEntity> {

    E findById(final long id);

    void setActive(final long id);

    void clearActive();

    void create(long id, String name, String description);

    void create(String name, String description);

    void update(String name, String description) throws NoActiveElementException;

    void delete() throws NoActiveElementException;
}
