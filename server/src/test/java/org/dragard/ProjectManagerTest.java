package org.dragard;

import static org.junit.Assert.assertTrue;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.dragard.projectmanager.api.repository.ProjectRepository;
import org.dragard.projectmanager.api.repository.TaskRepository;
import org.dragard.projectmanager.entity.Project;
import org.dragard.projectmanager.entity.Task;
import org.dragard.projectmanager.other.BatisTests;
import org.dragard.projectmanager.repository.ProjectMyBatisRepositoryImpl;
import org.dragard.projectmanager.repository.TaskMybatisRepositoryImpl;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.UUID;

/**
 * Unit test for simple ProjectManager.
 */
public class ProjectManagerTest
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void test2() throws Exception
    {
        File file = new File(Paths.get(this.getClass().getResource("/").toURI()).toFile(), "virg/dragard");
        System.out.println(file.getAbsolutePath());
        file.mkdirs();
        file = new File(file, "child.txt");
        file.createNewFile();
//        new File("res/mybatis-config.xml").createNewFile();
    }

    @Test
    public void testMore() throws Exception{
        File file = new File(Paths.get(this.getClass().getResource("/").toURI()).toFile(), "mybatis-config.xml");
        InputStream inputStream = new FileInputStream(file);
        SqlSessionFactory sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();

        try {
            BatisTests bt = session.getMapper(BatisTests.class);
            System.out.println(bt.selectTask("01d43cc2-6a9d-407c-9961-91b97778877c"));
        } finally {
            session.close();
        }

    }

    @Test
    public void test1() throws Exception{
        final TaskRepository taskRepository = new TaskMybatisRepositoryImpl();
        final Task task = new Task(UUID.randomUUID().toString(), "Name_asdfasf", "description_asdfasf",
                "ba7b5b1a-2ab0-4a5f-9a9d-c70734e0b005", "67f8dcc8-40e7-4541-8501-730096dad0f0");
        taskRepository.merge(task);
        Collection<Task> c = taskRepository.getElements();
        for (Task t: c){
            System.out.println(t);
        }
        System.out.println();
        task.setDescription("new_description");
        taskRepository.merge(task);
        c = taskRepository.getElementsByUserId("67f8dcc8-40e7-4541-8501-730096dad0f0");
        for (Task t: c){
            System.out.println(t);
        }
        System.out.println();
        final Task task1 = new Task(UUID.randomUUID().toString(), "Name_asdfasf2134", "desc1234ription_asdfasf",
                "ba7b5b1a-2ab0-4a5f-9a9d-c70734e0b005", "67f8dcc8-40e7-4541-8501-730096dad0f0");
        taskRepository.merge(task1);
        c = taskRepository.getElementsByUserId("67f8dcc8-40e7-4541-8501-730096dad0f0");
        for (Task t: c){
            System.out.println(t);
        }
        System.out.println();
        taskRepository.delete(task1.getId());
    }

    @Test
    public void test3() throws Exception{
        final ProjectRepository projectRepository = new ProjectMyBatisRepositoryImpl();
        final Project project = new Project(UUID.randomUUID().toString(), "Name_asdfasf", "description_asdfasf", "67f8dcc8-40e7-4541-8501-730096dad0f0");
        projectRepository.merge(project);
        Collection<Project> c = projectRepository.getElements();
        for (Project p: c){
            System.out.println(p);
        }
        System.out.println();
        project.setDescription("new_description");
        projectRepository.merge(project);
        c = projectRepository.getElementsByUserId("67f8dcc8-40e7-4541-8501-730096dad0f0");
        for (Project t: c){
            System.out.println(t);
        }
        System.out.println();
        final Project project1 = new Project(UUID.randomUUID().toString(), "Name_asdfasf2134", "desc1234ription_asdfasf",
                "67f8dcc8-40e7-4541-8501-730096dad0f0");
        projectRepository.merge(project1);
        c = projectRepository.getElementsByUserId("67f8dcc8-40e7-4541-8501-730096dad0f0");
        for (Project t: c){
            System.out.println(t);
        }
        System.out.println();
        projectRepository.delete(project1.getId());
    }
}

