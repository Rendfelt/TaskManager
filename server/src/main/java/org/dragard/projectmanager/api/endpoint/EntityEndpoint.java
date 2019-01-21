package org.dragard.projectmanager.api.endpoint;

import org.dragard.projectmanager.entity.AbstractEntity;
import org.dragard.projectmanager.entity.Response;

import java.util.Collection;

public interface EntityEndpoint<E extends AbstractEntity> extends Endpoint{

    Response delete(String id, String token);

    Response getView(String token);

    Response persist(Collection<E> elements, String token);
}
