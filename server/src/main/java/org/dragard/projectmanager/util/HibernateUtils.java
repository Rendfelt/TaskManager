package org.dragard.projectmanager.util;

import lombok.SneakyThrows;
import org.apache.deltaspike.jpa.api.transaction.TransactionScoped;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class HibernateUtils {

    private static SessionFactory sessionFactory;

    @Produces
    @TransactionScoped
    public static EntityManager getSession(){
        if (sessionFactory != null){
            return sessionFactory.openSession();
        }
        SessionFactory sf = getSessionFactoryByCfg("hibernate2.cfg.xml");
        EntityManager entityManager = sf.openSession();
        entityManager.getTransaction().begin();
        String databaseName = "test_project_manager25";
        try {
            entityManager.createNativeQuery("USE `" + databaseName + "`").executeUpdate();
        } catch (Exception e) {
            entityManager.createNativeQuery("CREATE DATABASE `" + databaseName + "`\n" +
                    "  CHARACTER SET utf8\n" +
                    "  COLLATE utf8_general_ci").executeUpdate();
            entityManager.createNativeQuery("USE `" + databaseName + "`").executeUpdate();
            createDatabaseFromConsole(databaseName);
        }
        sessionFactory = getSessionFactoryByCfg("hibernate.cfg.xml");
        return sessionFactory.openSession();
    }

    private static SessionFactory getSessionFactoryByCfg(String configName){
        StandardServiceRegistry standardRegistry =
                new StandardServiceRegistryBuilder().configure(configName).build();
        Metadata metaData =
                new MetadataSources(standardRegistry).getMetadataBuilder().build();
        return metaData.getSessionFactoryBuilder().build();
    }

    @SneakyThrows
    private static void createDatabaseFromConsole(String databaseName){
        final ProcessBuilder processBuilder = new ProcessBuilder();

        File dump = File.createTempFile("pref", "suf");
        InputStream is = HibernateUtils.class.getResourceAsStream("/dump_project_manager.sql");
        Files.copy(is, dump.toPath(), StandardCopyOption.REPLACE_EXISTING);

        final String login = "root";
        final String password = "root";
        String console;
        String param;
        String osName = System.getProperty("os.name");
        if (osName.toLowerCase().contains("windows")){
            console = "cmd.exe";
            param = "/c";
        } else {
            console = "bash";
            param = "-c";
        }
        String cmd = String.format("mysql -u %s -p%s -h localhost %s < \"%s\"", login, password, databaseName, dump.getAbsolutePath());
        processBuilder.command(console, param, cmd);
        try {
            Process process = processBuilder.start();
            StringBuilder output = new StringBuilder();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
