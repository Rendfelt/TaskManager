package org.dragard.projectmanager;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;

public class ClientApplication {

    public static void main(String[] args) throws Exception{
        WeldContainer weld = new Weld().initialize();
        weld.instance().select(Bootstrap.class).get().run();
    }

}
