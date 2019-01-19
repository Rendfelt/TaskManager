package org.dragard.projectmanager.server.other;

import org.dragard.projectmanager.server.api.ServiceLocator;
import org.dragard.projectmanager.server.api.endpoint.ProjectEndpoint;
import org.dragard.projectmanager.server.endpoint.AbstractEndpoint;
import org.dragard.projectmanager.server.entity.Project;

import java.util.Collection;

public class ProjectEndpointImpl extends AbstractEndpoint
    implements ProjectEndpoint {

    public ProjectEndpointImpl() {
    }

    public ProjectEndpointImpl(ServiceLocator serviceLocator) {
        super(serviceLocator);
    }


    @Override
    public String create(String name, String description) {
        return null;
    }

    @Override
    public String update(String id, String name, String description) {
        return null;
    }

    @Override
    public String delete(String id) {
        return null;
    }

    @Override
    public String getView() {
        return null;
    }

    @Override
    public String persist(Collection<Project> elements) {
        return null;
    }
}
