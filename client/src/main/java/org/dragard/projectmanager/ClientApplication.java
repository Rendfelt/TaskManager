package org.dragard.projectmanager;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ClientApplication {

    public static void main(String[] args) throws Exception{
        ApplicationContext context = new AnnotationConfigApplicationContext(ClientConfig.class);
        context.getBean(Bootstrap.class).run();
    }

}
