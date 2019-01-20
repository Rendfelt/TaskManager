package org.dragard.projectmanager.server.endpoint;


import org.dragard.projectmanager.server.api.ServiceLocator;
import org.dragard.projectmanager.server.api.endpoint.Endpoint;

public class AbstractEndpoint
    implements Endpoint {

    private ServiceLocator serviceLocator;

    public AbstractEndpoint() {
    }

    public AbstractEndpoint(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    public void setServiceLocator(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    public ServiceLocator getServiceLocator() {
        return serviceLocator;
    }
}
