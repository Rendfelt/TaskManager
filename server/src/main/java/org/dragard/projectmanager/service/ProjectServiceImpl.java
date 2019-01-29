package org.dragard.projectmanager.service;

import org.dragard.projectmanager.api.repository.JobRepository;
import org.dragard.projectmanager.api.repository.TaskRepository;
import org.dragard.projectmanager.api.repository.UserRepository;
import org.dragard.projectmanager.api.service.ProjectService;
import org.dragard.projectmanager.api.service.TaskService;
import org.dragard.projectmanager.entity.Project;
import org.dragard.projectmanager.entity.Task;
import org.dragard.projectmanager.util.HibernateUtils;
import org.jetbrains.annotations.Nullable;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class ProjectServiceImpl extends AbstractJobEntityService<Project>
    implements ProjectService {

    private TaskRepository taskRepository;
    private UserRepository userRepository;

    public ProjectServiceImpl(JobRepository<Project> repository, TaskRepository taskRepository, UserRepository userRepository) {
        super(repository);
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Project create(@Nullable String name, @Nullable String description, String userId) throws Exception {
        if (name == null || name.isEmpty()){
            throw new Exception("Name is empty");
        }
        if (description == null){
            description = "";
        }

        EntityManager entityManager = HibernateUtils.getSession();
        entityManager.getTransaction().begin();

        final Project project = Project.newInstance(name, description, userRepository.getElementById(userId, entityManager));
        Project project2 = getRepository().merge(project, entityManager);

        entityManager.getTransaction().commit();
        entityManager.close();
        return project2;
    }

    @Override
    public Project update(String id, String name, String description) throws Exception {
        if (id == null || id.isEmpty()){
            throw new Exception("No element with id");
        }
        final EntityManager entityManager = HibernateUtils.getSession();
        entityManager.getTransaction().begin();

        final Project project = getRepository().getElementById(id, entityManager);
        if (project == null){
            throw new Exception("No element with id");
        }
        if (name == null || name.isEmpty()){
            throw new Exception("Name is empty");
        }
        if (description == null){
            description = "";
        }

        project.setName(name);
        project.setDescription(description);
        final Project resultProject = getRepository().merge(project, entityManager);

        entityManager.getTransaction().commit();
        entityManager.close();

        return resultProject;
    }

    @Override
    public Project delete(String id) throws Exception {
        EntityManager entityManager = HibernateUtils.getSession();
        entityManager.getTransaction().begin();

        Project project = getRepository().getElementById(id, entityManager);
        if (project == null){
            System.out.println("No element deleted");
            return null;
        }
        List<Task> taskList = taskRepository.getElementsByProjectId(id, entityManager);
        if (taskList != null && !taskList.isEmpty()){
            for (Task t : taskList){
                taskRepository.delete(t.getId(), entityManager);
            }
        }

        Project project1 = getRepository().delete(id, entityManager);

        entityManager.getTransaction().commit();
        entityManager.close();


        return project1;
    }
}
