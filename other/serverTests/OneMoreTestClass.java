package org.dragard;

import lombok.SneakyThrows;
import org.dragard.projectmanager.api.service.AuthorizationService;
import org.dragard.projectmanager.entity.Task;
import org.dragard.projectmanager.entity.User;
import org.dragard.projectmanager.repository.ProjectHibernateRepository;
import org.dragard.projectmanager.repository.TaskHibernateRepository;
import org.dragard.projectmanager.repository.UserHibernateRepository;
import org.dragard.projectmanager.service.AuthorizationServiceImpl;
import org.dragard.projectmanager.service.TaskServiceImpl;
import org.dragard.projectmanager.service.UserServiceImpl;
import org.dragard.projectmanager.util.HibernateUtils;
import org.hibernate.Session;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.List;

public class OneMoreTestClass {

    @Test
    @SneakyThrows
    public void test(){
        System.out.println(System.getProperty("os.name"));
    }

    @Test
    @SneakyThrows
    public void test3(){
        EntityManager entityManager = HibernateUtils.getSession();
        entityManager.getTransaction().begin();
        String databaseName = "test_project_manager10";
        try {

            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Task> elementCriteria = cb.createQuery(Task.class);
            Root<Task> elementRoot = elementCriteria.from(Task.class);
            elementCriteria.select(elementRoot);
            elementCriteria.where(cb.equal(elementRoot.get("user").get("id"), "342cb5ea-45a7-4c5d-9756-fcc6084c9aa0"));
            List<Task> taskList = entityManager.createQuery(elementCriteria).getResultList();
            for (Task task : taskList){
                System.out.println(task);
            }
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Test
    @SneakyThrows
    public void test1(){
        EntityManager entityManager = TestHibernateUtils.getSession();
        entityManager.getTransaction().begin();
        entityManager.createNativeQuery("drop database `test_project_manager`;").executeUpdate();
    }

    @SneakyThrows
    public void createDatabaseFromConsole(String databaseName){
        final ProcessBuilder processBuilder = new ProcessBuilder();
        final File dump = new File(Paths.get(this.getClass().getResource("/").toURI()).toFile(), "test_dump_project_manager.sql");
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
