package org.dragard.projectmanager.service;

import javafx.beans.NamedArg;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.deltaspike.data.api.FullEntityRepository;
import org.dragard.projectmanager.api.annotation.NotEmpty;
import org.dragard.projectmanager.api.annotation.NullAndEmptyChecker;
import org.dragard.projectmanager.api.annotation.Preferred;
import org.dragard.projectmanager.api.repository.JobRepository;
import org.dragard.projectmanager.api.repository.ProjectRepository;
import org.dragard.projectmanager.api.repository.TaskRepository;
import org.dragard.projectmanager.api.repository.UserRepository;
import org.dragard.projectmanager.api.service.TaskService;
import org.dragard.projectmanager.entity.Project;
import org.dragard.projectmanager.entity.Task;
import org.dragard.projectmanager.entity.User;
import org.dragard.projectmanager.repository.ProjectDSRepository;
import org.dragard.projectmanager.repository.TaskDSRepository;
import org.dragard.projectmanager.repository.UserDSRepository;
import org.dragard.projectmanager.util.HibernateUtils;
import org.jetbrains.annotations.Nullable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Getter
@Setter
@Transactional
@NoArgsConstructor
@ApplicationScoped
@NullAndEmptyChecker
public class TaskServiceImpl extends AbstractJobEntityService<Task>
        implements TaskService {

    @Inject @Preferred
    private TaskDSRepository taskRepository;

    @Inject @Preferred
    private ProjectDSRepository projectRepository;

    @Inject @Preferred
    private UserDSRepository userRepository;

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

        final Project project = projectRepository.findBy(projectId);
        if (project == null){
            throw new Exception("No project with id");
        }
        final User user = userRepository.findBy(userId);
        if (user == null){
            throw new Exception("No user with id");
        }
        final Task task = Task.newInstance(name, description, user, project);
        return getRepository().merge(task);

    }

    @Override
    public Task update(
            @NamedArg (value = "id") @Nullable @NotEmpty String id,
            @NamedArg(value = "login") @Nullable @NotEmpty String name,
            @NamedArg (value = "description") String description,
            @NamedArg (value = "userId") @Nullable @NotEmpty String userId
    ){
        if (description == null){
            description = "";
        }
        final Task task = getRepository().findBy(id);
        if (task == null){
            throw new RuntimeException("No element with id");
        }
        task.setName(name);
        task.setDescription(description);
        return getRepository().merge(task);
    }
}
