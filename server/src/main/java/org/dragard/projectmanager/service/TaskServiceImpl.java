package org.dragard.projectmanager.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.dragard.projectmanager.api.repository.JobRepository;
import org.dragard.projectmanager.api.repository.ProjectRepository;
import org.dragard.projectmanager.api.repository.TaskRepository;
import org.dragard.projectmanager.api.repository.UserRepository;
import org.dragard.projectmanager.api.service.TaskService;
import org.dragard.projectmanager.entity.Project;
import org.dragard.projectmanager.entity.Task;
import org.dragard.projectmanager.entity.User;
import org.dragard.projectmanager.util.HibernateUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@Getter
@Setter
@NoArgsConstructor
@ApplicationScoped
public class TaskServiceImpl extends AbstractJobEntityService<Task>
        implements TaskService {

    @Inject
    private TaskRepository taskRepository;

    @Inject
    private ProjectRepository projectRepository;

    @Inject
    private UserRepository userRepository;

    /*public TaskServiceImpl(TaskRepository repository, UserRepository userRepository, ProjectRepository projectRepository) {
        super(repository);
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }*/

    @Override
    protected JobRepository<Task> getRepository() {
        return taskRepository;
    }

    @Override
    public Task create(String name, String description, String projectId, String userId) throws Exception {
        if (name == null || name.isEmpty()){
            throw new Exception("Name is empty");
        }
        if (description == null){
            description = "";
        }

        EntityManager entityManager = HibernateUtils.getSession();
        entityManager.getTransaction().begin();

        final Project project = projectRepository.getElementById(projectId, entityManager);
        if (project == null){
            throw new Exception("No project with id");
        }
        final User user = userRepository.getElementById(userId, entityManager);
        if (user == null){
            throw new Exception("No user with id");
        }

        final Task task = Task.newInstance(name, description, user, project);
        final Task resultTask = getRepository().merge(task, entityManager);

        entityManager.getTransaction().commit();
        entityManager.close();
        return resultTask;

    }

    @Override
    public Task update(String id, String name, String description, String userId) throws Exception {
        if (id == null || id.isEmpty()){
            throw new Exception("No element with id");
        }
        if (name == null || name.isEmpty()){
            throw new Exception("Name is empty");
        }
        if (description == null){
            description = "";
        }

        final EntityManager entityManager = HibernateUtils.getSession();
        entityManager.getTransaction().begin();

        final Task task = getRepository().getElementById(id, entityManager);
        if (task == null){
            throw new Exception("No element with id");
        }

        task.setName(name);
        task.setDescription(description);
        final Task resultTask = getRepository().merge(task, entityManager);

        entityManager.getTransaction().commit();
        entityManager.close();
        return resultTask;
    }
}
