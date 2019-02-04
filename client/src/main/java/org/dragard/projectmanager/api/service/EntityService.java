package org.dragard.projectmanager.api.service;

import org.dragard.projectmanager.endpoint.AbstractEntity;
import org.dragard.projectmanager.endpoint.Response;

public interface EntityService<E extends AbstractEntity> extends Service{

    Response delete(String id, String token);

    Response getView(String token);

//    Response persist(List<E> elements, String token) throws Exception;

}
