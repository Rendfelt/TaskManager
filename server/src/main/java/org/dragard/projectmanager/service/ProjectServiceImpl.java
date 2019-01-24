package org.dragard.projectmanager.service;

import org.dragard.projectmanager.api.repository.JobRepository;
import org.dragard.projectmanager.api.repository.Repository;
import org.dragard.projectmanager.api.service.ProjectService;
import org.dragard.projectmanager.api.service.TaskService;
import org.dragard.projectmanager.entity.Project;

import java.util.UUID;

public class ProjectServiceImpl extends AbstractJobEntityService<Project>
    implements ProjectService {

    private TaskService taskService;

    public ProjectServiceImpl(JobRepository<Project> repository, TaskService taskService) {
        super(repository);
        this.taskService = taskService;
    }

    @Override
    public Project create(String name, String description, String userId) throws Exception {
        if (name == null || name.isEmpty()){
            throw new Exception("Name is empty");
        }
        if (description == null){
            description = "";
        }
        return getRepository().merge(new Project(UUID.randomUUID().toString(), name, description, userId));
    }

    @Override
    public Project update(String id, String name, String description) throws Exception {
        if (getRepository().getElementById(id) == null){
            throw new Exception("No element with id");
        }
        Project project = null;
        project = getRepository().getElementById(id);
        if (name == null || name.isEmpty()){
            throw new Exception("Name is empty");
        }
        if (description == null){
            description = "";
        }
        return getRepository().merge(new Project(id, name, description, project.getUserId()));
    }

    @Override
    public Project delete(String id) throws Exception {
        Project project = super.delete(id);
        taskService.deleteTasksByProjectId(project.getId());
        return project;
    }
}
