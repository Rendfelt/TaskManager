package org.dragard.projectmanager.endpoint;


import org.dragard.projectmanager.api.ServiceLocator;
import org.dragard.projectmanager.api.endpoint.Endpoint;

public class AbstractEndpoint
    implements Endpoint {

    private ServiceLocator serviceLocator;

    public AbstractEndpoint() {
    }

    public AbstractEndpoint(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    @Override
    public ServiceLocator getServiceLocator() {
        return serviceLocator;
    }
}
