package org.dragard.projectmanager.client.api.service;

import org.dragard.projectmanager.client.endpoint.AbstractEntity;
import org.dragard.projectmanager.client.endpoint.Response;

import java.util.List;

public interface EntityService<E extends AbstractEntity> extends Service{

    Response delete(String id, String token) throws Exception;

    Response getView(String token) throws Exception;

    Response persist(List<E> elements, String token) throws Exception;

}
