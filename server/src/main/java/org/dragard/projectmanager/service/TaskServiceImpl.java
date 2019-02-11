package org.dragard.projectmanager.service;

import javafx.beans.NamedArg;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.dragard.projectmanager.api.annotation.NotEmpty;
import org.dragard.projectmanager.api.annotation.NullAndEmptyChecker;
import org.dragard.projectmanager.repository.ProjectRepository;
import org.dragard.projectmanager.repository.TaskRepository;
import org.dragard.projectmanager.repository.UserRepository;
import org.dragard.projectmanager.api.service.TaskService;
import org.dragard.projectmanager.entity.Project;
import org.dragard.projectmanager.entity.Task;
import org.dragard.projectmanager.entity.User;
import org.jetbrains.annotations.Nullable;
import javax.inject.Inject;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Getter
@Setter
@Transactional
@NoArgsConstructor
@Component
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
    protected TaskRepository getRepository() {
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

        final Project project = projectRepository.findById(projectId).orElse(null);
        if (project == null){
            throw new Exception("No project with id");
        }
        final User user = userRepository.findById(userId).orElse(null);
        if (user == null){
            throw new Exception("No user with id");
        }
        final Task task = Task.newInstance(name, description, user, project);
        return getRepository().save(task);
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
        final Task task = getRepository().findById(id).orElse(null);
        if (task == null){
            throw new RuntimeException("No element with id");
        }
        task.setName(name);
        task.setDescription(description);
        return getRepository().save(task);
    }
}
