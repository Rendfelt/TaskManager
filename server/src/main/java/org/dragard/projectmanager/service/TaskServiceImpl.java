package org.dragard.projectmanager.service;

import javafx.beans.NamedArg;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.dragard.projectmanager.api.annotation.NotEmpty;
import org.dragard.projectmanager.api.annotation.NullAndEmptyChecker;
import org.dragard.projectmanager.api.repository.JobRepository;
import org.dragard.projectmanager.api.repository.ProjectRepository;
import org.dragard.projectmanager.api.repository.TaskRepository;
import org.dragard.projectmanager.api.repository.UserRepository;
import org.dragard.projectmanager.api.service.TaskService;
import org.dragard.projectmanager.entity.Project;
import org.dragard.projectmanager.entity.Task;
import org.dragard.projectmanager.entity.User;
import org.dragard.projectmanager.util.HibernateUtils;
import org.jetbrains.annotations.Nullable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@Getter
@Setter
@NoArgsConstructor
@ApplicationScoped
@NullAndEmptyChecker
public class TaskServiceImpl extends AbstractJobEntityService<Task>
        implements TaskService {

    @Inject
    private TaskRepository taskRepository;

    @Inject
    private ProjectRepository projectRepository;

    @Inject
    private UserRepository userRepository;

    @Override
    protected JobRepository<Task> getRepository() {
        return taskRepository;
    }

    @Override
    public Task create(
            @NamedArg(value = "login") @Nullable @NotEmpty String name,
            @NamedArg (value = "description") String description,
            @NamedArg (value = "projectId") @Nullable @NotEmpty String projectId,
            @NamedArg (value = "userId") @Nullable @NotEmpty String userId
    ) throws Exception {
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
    public Task update(
            @NamedArg (value = "id") @Nullable @NotEmpty String id,
            @NamedArg(value = "login") @Nullable @NotEmpty String name,
            @NamedArg (value = "description") String description,
            @NamedArg (value = "userId") @Nullable @NotEmpty String userId
    ) throws Exception {
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
