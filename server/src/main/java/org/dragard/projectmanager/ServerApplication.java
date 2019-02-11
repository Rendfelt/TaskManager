package org.dragard.projectmanager;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ServerApplication {

    public static void main(String[] args){
        ApplicationContext context = new AnnotationConfigApplicationContext(ServerConfig.class);
        context.getBean(Bootstrap.class).run();

        /*WeldContainer weld = new Weld().initialize();
        weld.instance().select(Bootstrap.class).get().run();*/
/*        final Bootstrap bootstrap = new Bootstrap();
        bootstrap.run();*/

    }

}
