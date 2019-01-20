package org.dragard.projectmanager.server.endpoint;


import org.dragard.projectmanager.server.Bootstrap;
import org.dragard.projectmanager.server.api.endpoint.Endpoint;

public class AbstractEndpoint
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
