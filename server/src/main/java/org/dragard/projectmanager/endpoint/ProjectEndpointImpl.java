package org.dragard.projectmanager.endpoint;

import org.dragard.projectmanager.api.ServiceLocator;
import org.dragard.projectmanager.api.endpoint.ProjectEndpoint;
import org.dragard.projectmanager.exception.NoElementWithIdException;
import org.dragard.projectmanager.exception.NoNameException;

public class ProjectEndpointImpl extends AbstractEndpoint
    implements ProjectEndpoint {

    public ProjectEndpointImpl() {
    }

    public ProjectEndpointImpl(ServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    @Override
    public void create(String name, String description, String userId) throws NoNameException {

    }

    @Override
    public void update(String id, String name, String description, String userId) throws NoNameException, NoElementWithIdException {

    }

}
