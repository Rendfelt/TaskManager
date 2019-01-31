package org.dragard.projectmanager.util;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.dragard.projectmanager.api.endpoint.service.EndpointService;
import org.dragard.projectmanager.api.service.AuthorizationService;
import org.dragard.projectmanager.api.service.Service;
import org.dragard.projectmanager.entity.Response;
import org.dragard.projectmanager.entity.User;

import java.io.*;
import java.lang.reflect.Proxy;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class UtilClass {

    private static Connection connection;

    public static Connection getConnection() {
        return connection;
    }

    static {
        try {
            final Properties props = new Properties();
            final File propertyFile = new File(Paths.get(UtilClass.class.getResource("/").toURI()).toFile(), "database.properties");

            try (InputStream in = Files.newInputStream(propertyFile.toPath()))
            {
                props.load(in);
            }

            String url = props.getProperty("jdbc.url");
            String username = props.getProperty("jdbc.username");
            String password = props.getProperty("jdbc.password");

            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            System.out.println("Cannot connect to DB");
            e.printStackTrace();
        }
    }

    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            File file = new File(Paths.get(UtilClass.class.getResource("/").toURI()).toFile(), "mybatis-config.xml");
            InputStream inputStream = new FileInputStream(file);
            sqlSessionFactory =
                    new SqlSessionFactoryBuilder().build(inputStream);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private UtilClass() {
    }



    public static byte[] serializeExceptionToByteArray(Exception e){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            new ObjectOutputStream(baos).writeObject(e);
        } catch (IOException e1) {
            e1.printStackTrace();
            return null;
        }
        return baos.toByteArray();
    }

    public static String createToken(User user){
        return user.toString();
    }

    public static boolean checkToken(String token, Response response) throws Exception {
        if (token == null){
            throw new Exception("Invalid token, access denied");
        }
        return true;
    }

    public static String getPassword(String s) throws NoSuchAlgorithmException {
        if (s == null || s.isEmpty()){
            return null;
        }
        s = new String(MessageDigest.getInstance("MD5").digest(s.getBytes(StandardCharsets.UTF_8)));
        return String.valueOf(s.hashCode());
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

    public static EndpointService getEndpointProxy(Class clazz, EndpointService instance, AuthorizationService authorizationService){
        final Class[] classes = new Class[] {clazz};
        final ClassLoader classLoader = clazz.getClassLoader();
        final EndpointServiceHandler endpointServiceHandler = new EndpointServiceHandler(instance, authorizationService);

        return (EndpointService) Proxy.newProxyInstance(classLoader, classes, endpointServiceHandler);
    }

    public static Service getServiceProxy(Class clazz, Service instance, AuthorizationService authorizationService){
        final Class[] classes = new Class[] {clazz};
        final ClassLoader classLoader = clazz.getClassLoader();
        final EndpointServiceHandler responseCheckHandler = new EndpointServiceHandler(instance, authorizationService);

        return (Service) Proxy.newProxyInstance(classLoader, classes, responseCheckHandler);
    }
}
