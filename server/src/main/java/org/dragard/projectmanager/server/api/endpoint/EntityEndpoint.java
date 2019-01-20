package org.dragard.projectmanager.server.api.endpoint;

import org.dragard.projectmanager.server.entity.AbstractEntity;
import org.dragard.projectmanager.server.entity.Response;

import java.util.Collection;

public interface EntityEndpoint<E extends AbstractEntity> extends Endpoint{

    Response delete(String id, String token);

    Response getView(String token);

    Response persist(Collection<E> elements, String token);
}
