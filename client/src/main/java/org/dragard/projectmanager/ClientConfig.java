package org.dragard.projectmanager;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.inject.Inject;

@Configuration
//@PropertySource({ "classpath:persistence-mysql.properties" })
@ComponentScan({ "org.dragard.projectmanager" })
public class ClientConfig {

    @Inject
    private Environment env;



}
