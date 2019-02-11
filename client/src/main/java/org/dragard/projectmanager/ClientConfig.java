package org.dragard.projectmanager;

import org.dragard.projectmanager.util.ServiceInterceptor;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

import javax.inject.Inject;


@Configuration
@EnableAspectJAutoProxy
@ComponentScan({ "org.dragard.projectmanager" })
public class ClientConfig {

    @Inject
    private Environment env;

    @Bean
    public ServiceInterceptor serviceInterceptor(){
        return new ServiceInterceptor();
    }

}
