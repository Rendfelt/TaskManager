package org.dragard.projectmanager;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

public class ClientApplication {

    public static void main(String[] args) throws Exception{
        WeldContainer weld = new Weld().initialize();
        weld.instance().select(Bootstrap.class).get().run();
    }

}
