package org.dragard.projectmanager.api.endpoint.service;

import javafx.beans.NamedArg;
import org.dragard.projectmanager.entity.Project;
import org.dragard.projectmanager.entity.Response;

import javax.jws.WebService;

public interface ProjectEndpointService extends EntityEndpointService<Project> {

    Response create(
            @NamedArg(value = "name") String name,
            @NamedArg(value = "description") String description,
            @NamedArg(value = "token") String token
    );

    Response update(
            @NamedArg(value = "id") String id,
            @NamedArg(value = "name") String name,
            @NamedArg(value = "description") String description,
            @NamedArg(value = "token")String token
    );
}
