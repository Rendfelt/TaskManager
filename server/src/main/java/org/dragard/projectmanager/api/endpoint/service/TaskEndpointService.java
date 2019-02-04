package org.dragard.projectmanager.api.endpoint.service;

import javafx.beans.NamedArg;
import org.dragard.projectmanager.entity.Response;
import org.dragard.projectmanager.entity.Task;
import javax.jws.WebService;

@WebService
public interface TaskEndpointService extends EntityEndpointService<Task> {

    Response create(
            @NamedArg(value = "name") String name,
            @NamedArg(value = "description") String description,
            @NamedArg(value = "projectId") String projectId,
            @NamedArg(value = "token") String token
    );

    Response update(
            @NamedArg(value = "id") String id,
            @NamedArg(value = "name") String name,
            @NamedArg(value = "description") String description,
            @NamedArg(value = "token") String token
    );
}
