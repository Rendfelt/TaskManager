package org.dragard.projectmanager.endpoint;


import org.dragard.projectmanager.Bootstrap;
import org.dragard.projectmanager.api.endpoint.Endpoint;

public abstract class AbstractEndpoint
    implements Endpoint {

    private Bootstrap bootstrap;

    public AbstractEndpoint() {
    }

    public AbstractEndpoint(Bootstrap bootstrap) {
        this.bootstrap = bootstrap;
    }

    public void setBootstrap(Bootstrap bootstrap) {
        this.bootstrap = bootstrap;
    }

    public Bootstrap getBootstrap() {
        return bootstrap;
    }
}
