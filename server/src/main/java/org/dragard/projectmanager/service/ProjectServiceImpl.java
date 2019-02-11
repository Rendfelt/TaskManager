package org.dragard.projectmanager.service;

import javafx.beans.NamedArg;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.dragard.projectmanager.api.annotation.NotEmpty;
import org.dragard.projectmanager.api.annotation.NullAndEmptyChecker;
import org.dragard.projectmanager.repository.ProjectRepository;
import org.dragard.projectmanager.repository.UserRepository;
import org.dragard.projectmanager.api.service.ProjectService;
import org.dragard.projectmanager.entity.Project;
import org.dragard.projectmanager.entity.Task;
import org.dragard.projectmanager.repository.TaskRepository;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Getter
@Setter
@Transactional
@NoArgsConstructor
@Component
@NullAndEmptyChecker
public class ProjectServiceImpl extends AbstractJobEntityService<Project>
    implements ProjectService {

    @Inject
    private TaskRepository taskRepository;

    @Inject
    private ProjectRepository projectRepository;

    @Inject
    private UserRepository userRepository;

    @Override
    protected ProjectRepository getRepository() {
        return projectRepository;
    }

    @Override
    public Project create(
            @NamedArg(value = "name") @Nullable @NotEmpty String name,
            @NamedArg(value = "description") String description,
            @NamedArg(value = "userId") @Nullable @NotEmpty String userId
    ) {
        if (description == null){
            description = "";
        }

        final Project project = Project.newInstance(name, description, userRepository.findById(userId).orElse(null));

        return getRepository().save(project);
    }

    @Override
    public Project update(
            @NamedArg(value = "id") @Nullable @NotEmpty String id,
            @NamedArg(value = "name") @Nullable @NotEmpty String name,
            @NamedArg(value = "description") String description
    ) {
        final Project project = getRepository().findById(id).orElse(null);
        if (project == null){
            throw new RuntimeException("No element with id");
        }
        if (description == null){
            description = "";
        }
        project.setName(name);
        project.setDescription(description);

        return getRepository().save(project);
    }

    @Override
    public Project delete(
            @NamedArg(value = "id") @Nullable @NotEmpty String id
    ){
        Project project = getRepository().findById(id).orElse(null);
        if (project == null){
            throw new RuntimeException("No element deleted");
        }
        List<Task> taskList = taskRepository.findByProject_id(id);
        if (taskList != null && !taskList.isEmpty()){
            for (Task t : taskList){
                taskRepository.delete(t);
            }
        }
        getRepository().delete(project);
        return project;
    }
}
