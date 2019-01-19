package org.dragard.projectmanager.server.api.endpoint;

import org.dragard.projectmanager.server.entity.AbstractEntity;

import java.util.Collection;

public interface EntityEndpoint<E extends AbstractEntity> extends Endpoint{

    String delete(String id);

    String getView();

    String persist(Collection<E> elements);
}
