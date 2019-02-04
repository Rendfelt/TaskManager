package org.dragard.projectmanager.api.endpoint.service;

import javafx.beans.NamedArg;
import org.dragard.projectmanager.entity.AbstractEntity;
import org.dragard.projectmanager.entity.Response;

import javax.jws.WebService;
import java.util.Collection;

public interface EntityEndpointService<E extends AbstractEntity> extends EndpointService {

    Response delete(
            @NamedArg(value = "id") String id,
            @NamedArg(value = "token") String token
    );

    Response getView(
            @NamedArg(value = "token") String token
    );

    Response persist(
            @NamedArg(value = "elements") Collection<E> elements,
            @NamedArg(value = "token") String token
    );
}
