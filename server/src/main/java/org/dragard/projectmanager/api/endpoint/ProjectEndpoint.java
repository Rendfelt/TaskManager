package org.dragard.projectmanager.api.endpoint;

import org.dragard.projectmanager.exception.NoElementWithIdException;
import org.dragard.projectmanager.exception.NoNameException;

public interface ProjectEndpoint extends Endpoint {
    void create(String name, String description, String userId) throws NoNameException;

    void update(String id, String name, String description, String userId) throws NoNameException, NoElementWithIdException;
}
