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
import org.dragard.projectmanager.api.service.ProjectService;
import org.dragard.projectmanager.api.service.TaskService;
import org.dragard.projectmanager.entity.Project;
import org.dragard.projectmanager.entity.Task;
import org.dragard.projectmanager.repository.ProjectDSRepository;
import org.dragard.projectmanager.repository.TaskDSRepository;
import org.dragard.projectmanager.repository.UserDSRepository;
import org.dragard.projectmanager.util.HibernateUtils;
import org.jetbrains.annotations.Nullable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Getter
@Setter
@Transactional
@NoArgsConstructor
@ApplicationScoped
@NullAndEmptyChecker
public class ProjectServiceImpl extends AbstractJobEntityService<Project>
    implements ProjectService {

    @Inject @Preferred
    private TaskDSRepository taskRepository;

    @Inject @Preferred
    private ProjectDSRepository projectRepository;

    @Inject @Preferred
    private UserDSRepository userRepository;

    @Override
    protected JobRepository<Project> getRepository() {
        return projectRepository;
    }

    @Override
    public Project create(
            @NamedArg(value = "name") @Nullable @NotEmpty String name,
            @NamedArg(value = "description") String description,
            @NamedArg(value = "name") @Nullable @NotEmpty String userId
    ) {
        if (description == null){
            description = "";
        }

        final Project project = Project.newInstance(name, description, userRepository.findBy(userId));

        return getRepository().merge(project);
    }

    @Override
    public Project update(
            @NamedArg(value = "id") @Nullable @NotEmpty String id,
            @NamedArg(value = "name") @Nullable @NotEmpty String name,
            @NamedArg(value = "description") String description
    ) {
        final Project project = getRepository().findBy(id);
        if (project == null){
            throw new RuntimeException("No element with id");
        }
        if (description == null){
            description = "";
        }
        project.setName(name);
        project.setDescription(description);

        return getRepository().merge(project);
    }

    @Override
    public Project delete(
            @NamedArg(value = "id") @Nullable @NotEmpty String id
    ){
        Project project = getRepository().findBy(id);
        if (project == null){
            throw new RuntimeException("No element deleted");
        }
        List<Task> taskList = taskRepository.getElementsByProjectId(id);
        if (taskList != null && !taskList.isEmpty()){
            for (Task t : taskList){
                taskRepository.remove(t);
            }
        }
        getRepository().remove(project);
        return project;
    }
}
