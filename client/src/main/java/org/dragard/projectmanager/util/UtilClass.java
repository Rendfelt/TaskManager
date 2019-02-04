package org.dragard.projectmanager.util;

import org.dragard.projectmanager.api.annotation.Preferred;
import org.dragard.projectmanager.api.service.AuthorizationService;
import org.dragard.projectmanager.api.service.Service;
import org.dragard.projectmanager.endpoint.AuthorizationEndpointImplService;
import org.dragard.projectmanager.endpoint.Response;
import org.dragard.projectmanager.service.AuthorizationServiceImpl;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.xml.bind.DatatypeConverter;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.lang.reflect.Proxy;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UtilClass {

    private UtilClass() {
    }

    public static void checkResponse(Response response) throws Exception {
        if (response.getException() == null) {
            return;
        }
        throw (Exception) new ObjectInputStream(new ByteArrayInputStream(response.getException())).readObject();
    }

    public static String getPassword(String s) throws NoSuchAlgorithmException {
        if (s == null || s.isEmpty()){
            return null;
        }
        byte[] digest = MessageDigest.getInstance("MD5").digest(s.getBytes(StandardCharsets.UTF_8));
        return DatatypeConverter.printHexBinary(digest).toUpperCase();
    }

    public static Service getServiceProxy(Class clazz, Service instance){
        final Class[] classes = new Class[] {clazz};
        final ClassLoader classLoader = clazz.getClassLoader();
        final ResponseCheckHandler responseCheckHandler = new ResponseCheckHandler(instance);

        return (Service) Proxy.newProxyInstance(classLoader, classes, responseCheckHandler);
    }

    @Produces
    @Preferred
    @ApplicationScoped
    public static AuthorizationService getAuthorizationService(AuthorizationServiceImpl authorizationService) {
        authorizationService.setAuthorizationEndpoint(new AuthorizationEndpointImplService());
        return authorizationService;
    }

}
