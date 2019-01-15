package org.dragard.projectmanager.service;

import org.dragard.projectmanager.api.repository.ProjectRepository;
import org.dragard.projectmanager.api.repository.TaskRepository;
import org.dragard.projectmanager.api.service.TaskService;
import org.dragard.projectmanager.entity.Project;
import org.dragard.projectmanager.entity.Task;
import org.dragard.projectmanager.exception.NoElementWithIdException;
import org.dragard.projectmanager.exception.NoNameException;

import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

public class TaskServiceImpl extends AbstractService<Task>
        implements TaskService {

    private final ProjectRepository projectRepository;

    public TaskServiceImpl(TaskRepository repository, ProjectRepository projectRepository) {
        super(repository);
        this.projectRepository = projectRepository;
    }

    @Override
    public void create(String name, String description, String projectId) throws NoNameException, NoElementWithIdException {
        if (projectRepository.getElementById(projectId) == null){
            throw new NoElementWithIdException();
        }
        if (name == null || name.isEmpty()){
            throw new NoNameException();
        }
        if (description == null){
            description = "";
        }
        getRepository().create(new Task(UUID.randomUUID().toString(), name, description, projectId));
    }

    @Override
    public void update(String id, String name, String description) throws NoNameException, NoElementWithIdException {
        if (getRepository().getElementById(id) == null){
            throw new NoElementWithIdException();
        }
        if (name == null || name.isEmpty()){
            throw new NoNameException();
        }
        if (description == null){
            description = "";
        }
        getRepository().update(new Task(id, name, description, getRepository().getElementById(id).getProjectId()));
    }

    @Override
    public void deleteTasksByProjectId(String projectId) {
        Iterator<Task> iterator = getRepository().getElements().values().iterator();
        while (iterator.hasNext()){
            Task task = iterator.next();
            if (task.getProjectId().equals(projectId)){
                iterator.remove();
            }
        }
    }
}
