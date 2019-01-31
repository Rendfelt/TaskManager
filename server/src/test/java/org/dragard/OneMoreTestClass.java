package org.dragard;

import lombok.SneakyThrows;
import org.dragard.projectmanager.api.service.AuthorizationService;
import org.dragard.projectmanager.repository.UserHibernateRepository;
import org.dragard.projectmanager.service.AuthorizationServiceImpl;
import org.dragard.projectmanager.service.UserServiceImpl;
import org.junit.Test;

public class OneMoreTestClass {

    @Test
    @SneakyThrows
    public void test(){

/*        AuthorizationService as = AuthorizationServiceImpl.getInstance(new UserServiceImpl(new UserHibernateRepository()));
        as.login("login123", "password23424");*/
    }
}
