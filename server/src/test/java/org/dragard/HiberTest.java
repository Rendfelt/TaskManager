package org.dragard;

import lombok.SneakyThrows;
import org.dragard.projectmanager.api.repository.ProjectRepository;
import org.dragard.projectmanager.api.repository.TaskRepository;
import org.dragard.projectmanager.api.repository.UserRepository;
import org.dragard.projectmanager.api.service.ProjectService;
import org.dragard.projectmanager.api.service.UserService;
import org.dragard.projectmanager.entity.Project;
import org.dragard.projectmanager.entity.Task;
import org.dragard.projectmanager.entity.User;
import org.dragard.projectmanager.repository.ProjectHibernateRepository;
import org.dragard.projectmanager.repository.TaskHibernateRepository;
import org.dragard.projectmanager.repository.UserHibernateRepository;
import org.dragard.projectmanager.service.ProjectServiceImpl;
import org.dragard.projectmanager.service.UserServiceImpl;
import org.dragard.projectmanager.util.HibernateUtils;
import org.dragard.projectmanager.util.UtilClass;
import org.hibernate.Session;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Unit test for simple ProjectManager.
 */
public class HiberTest
{

    @Test
    @SneakyThrows
    public void testRep()
    {
        EntityManager entityManager = HibernateUtils.getSession();
        entityManager.getTransaction().begin();

        UserRepository ur = new UserHibernateRepository();
        TaskHibernateRepository tr = new TaskHibernateRepository();
        ProjectRepository pr = new ProjectHibernateRepository();

        /*for (int i = 0; i < 5; i++) {
            Task task = getNewTask();
            Project project = pr.getElementById("2423fc46-3dbb-404c-bd2b-9065bf4b5586", entityManager);
            task.setProject(project);
            task.setUser(project.getUser());
            tr.merge(task, entityManager);
        }*/
        String projectId = "2423fc46-3dbb-404c-bd2b-9065bf4b5586";

        Collection<Task> list = tr.getElementsByProjectId(projectId, entityManager);
        for (Task task : list){
            System.out.println(task);
        }

        entityManager.getTransaction().commit();
        entityManager.close();

    }

    @Test
    @SneakyThrows
    public void testUserGetById()
    {
        EntityManager entityManager = HibernateUtils.getSession();
        entityManager.getTransaction().begin();
        UserRepository ur = new UserHibernateRepository();
        User user = ur.getElementById("asdffdsa", entityManager);
        System.out.println(user);
        entityManager.getTransaction().commit();
        entityManager.close();

    }

    @Test
    @SneakyThrows
    public void testProjectServiceCreateUpdate()
    {
        EntityManager entityManager = HibernateUtils.getSession();
        entityManager.getTransaction().begin();

        ProjectRepository projectRepository = new ProjectHibernateRepository();
        UserRepository userRepository = new UserHibernateRepository();
        TaskRepository taskRepository = new TaskHibernateRepository();

        ProjectService ps = new ProjectServiceImpl(projectRepository, taskRepository, userRepository);
        String userId = "1fb5ed23-6d0e-4f6f-bb03-8552f54bcbbf";
        Project project = ps.create("SomeName+", "SomeDescription", userId);
        System.out.println(project);
        Project project1 = ps.update(project.getId(), "SomeNewName", "BadDescription");
//        System.out.println(project1);
        System.out.println("12345");
        entityManager.getTransaction().commit();
        entityManager.close();
        System.out.println(project);
        System.out.println(project1);
    }

    @Test
    @SneakyThrows
    public void testGetByLogin()
    {
//        String login = "login980";
        EntityManager entityManager = HibernateUtils.getSession();

        entityManager.getTransaction().begin();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<User> userCriteria = cb.createQuery(User.class);
        Root<User> userRoot = userCriteria.from(User.class);
        userCriteria.select(userRoot);
        userCriteria.where(cb.equal(userRoot.get("login"), "login491"));
        User user = entityManager.createQuery(userCriteria)
                .getSingleResult();
        System.out.println(user);

    }



    @Test
    @SneakyThrows
    public void testGetByUserId()
    {
//        String login = "login980";
        EntityManager entityManager = HibernateUtils.getSession();

        entityManager.getTransaction().begin();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Project> userCriteria = cb.createQuery(Project.class);
        Root<Project> userRoot = userCriteria.from(Project.class);
        userCriteria.select(userRoot);
        userCriteria.where(cb.equal(userRoot.get("user").get("id"), "fd1bee5e-fb6b-42b8-b091-917ea658a4aa"));
        List<Project> projects = entityManager.createQuery(userCriteria)
                .getResultList();
        for (Project p : projects){
            System.out.println(p);
        }
        entityManager.close();
    }



    @Test
    @SneakyThrows
    public void testUserMerge()
    {
        EntityManager entityManager = HibernateUtils.getSession();
        entityManager.getTransaction().begin();
        UserRepository ur = new UserHibernateRepository();
        User tempUser = getNewUser();
        System.out.println(tempUser);
        User user = ur.merge(tempUser, entityManager);
        System.out.println(user);
        entityManager.getTransaction().commit();
        System.out.println(user);
        entityManager.close();
    }

    @Test
    public void createUser()
    {
        User user = getNewUser();
        System.out.println(user);
        saveObjects(new Object[] {user});
    }

    @Test
    public void test22()
    {
        System.out.println(tryT());
    }


    public int tryT()
    {
        try {

            throw new Exception();
        } catch (Exception e) {
            return 2;
        } finally {
            return 1;
        }
    }

    @Test
    @SneakyThrows
    public void createAll()
    {
        for (int i = 0; i < 3; i++) {
            UserRepository ur = new UserHibernateRepository();
            UserService us = new UserServiceImpl(ur);
            User user = us.getElementByLogin("test");
            Project project = getNewProject();
            project.setUser(user);
            Task task = getNewTask();
            task.setProject(project);
            task.setUser(project.getUser());
            Task task2 = getNewTask();
            task2.setProject(project);
            task2.setUser(project.getUser());
            saveObjects(new Object[] {project, task, task2});
        }
    }

    @Test
    public void createProject()
    {
        User user = (User) getObject(User.class, "6aabaf9c-ca81-49af-906d-278786271bc5");
        Project project = getNewProject();
        project.setUser(user);
//        user.getProjects().add(project);
//        System.out.println(project);
        saveObjects(new Object[] {project});
    }

    @Test
    public void createTask()
    {

        User user = (User) getObject(User.class, "6aabaf9c-ca81-49af-906d-278786271bc5");
        Project project = (Project) getObject(Project.class, "2b085403-3eb9-4e51-afb0-929d3e30365f");
        Task task = getNewTask();
        task.setProject(project);
        task.setUser(user);
//        user.getProjects().add(project);
//        System.out.println(project);
        saveObjects(new Object[] {task});
    }


    @Test
    public void deleteTask()
    {
        Task task = (Task) getObject(Task.class, "f00286ca-511c-45d2-9967-c64e48d33718");
        deleteObject(task);
    }

    @Test
    public void deleteProject()
    {
        Project project = (Project) getObject(Project.class, "c56849a6-e408-45a6-aee5-01082b20f57a");
        deleteObject(project);
    }

    @Test
    public void deleteUser()
    {
        User user = (User) getObject(User.class, "6aabaf9c-ca81-49af-906d-278786271bc5");
        deleteObject(user);
    }

    @Test
    @SneakyThrows
    public void testArrayList()
    {
        UserRepository ur = new UserHibernateRepository();
        UserService us = new UserServiceImpl(ur);
        User user = us.getElementByLogin("asdfew");
        System.out.println(user);
    }

    @SneakyThrows
    private User getNewUser()
    {
        return User.newInstance("login" + r(), UtilClass.getPassword("password"));
    }

    @SneakyThrows
    private Task getNewTask()
    {
        return Task.newInstance("Task_Name" + r(), "Task_Description_" + r(), null, null);
    }

    @SneakyThrows
    private Project getNewProject()
    {
        return Project.newInstance("Project_Name_" + r(), "Project_Description_" + r(),null );
    }

    private int r(){
        return (int) (Math.random() * 1000);
    }

    private int br(){
        return (int) (Math.random() * 1000_000);
    }

    private void saveObjects(Object[] o){
        Session session = HibernateUtils.getSession();
        session.beginTransaction();
        for (Object o1: o){
            session.save(o1);
        }
        session.getTransaction().commit();
        session.close();
    }

    private void updateObjects(Object[] o){
        Session session = HibernateUtils.getSession();
        session.beginTransaction();
        for (Object o1: o){
            session.update(o1);
        }
        session.getTransaction().commit();
        session.close();
    }

    private Object getObject(Class clazz, Serializable id){
        Session session = HibernateUtils.getSession();
        session.beginTransaction();
        Object o = session.get(clazz, id);
        session.getTransaction().commit();
        session.close();
        return o;
    }

    private void deleteObject(Object o){
        Session session = HibernateUtils.getSession();
        session.beginTransaction();
        session.delete(o);
        session.getTransaction().commit();
        session.close();
    }
}

