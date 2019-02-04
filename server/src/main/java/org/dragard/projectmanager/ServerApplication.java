package org.dragard.projectmanager;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

public class ServerApplication {

    public static void main(String[] args) throws Exception {
        WeldContainer weld = new Weld().initialize();
        weld.instance().select(Bootstrap.class).get().run();
/*        final Bootstrap bootstrap = new Bootstrap();
        bootstrap.run();*/

    }

}
