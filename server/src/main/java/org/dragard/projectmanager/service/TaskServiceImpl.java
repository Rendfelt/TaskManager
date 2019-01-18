package org.dragard.projectmanager.service;

import org.dragard.projectmanager.api.repository.TaskRepository;
import org.dragard.projectmanager.api.service.TaskService;
import org.dragard.projectmanager.entity.Task;
import org.dragard.projectmanager.exception.NoElementWithIdException;
import org.dragard.projectmanager.exception.NoNameException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TaskServiceImpl extends AbstractJobEntityService<Task>
        implements TaskService {

    public TaskServiceImpl(TaskRepository repository) {
        super(repository);
    }

    @Override
    public void create(String name, String description, String projectId, String userId) throws NoNameException {
        if (name == null || name.isEmpty()){
            throw new NoNameException();
        }
        if (description == null){
            description = "";
        }
        getRepository().merge(new Task(UUID.randomUUID().toString(), name, description, projectId, userId));
    }

    @Override
    public void update(String id, String name, String description, String userId) throws NoNameException, NoElementWithIdException {
        if (getRepository().getElementById(id) == null){
            throw new NoElementWithIdException();
        }
        if (name == null || name.isEmpty()){
            throw new NoNameException();
        }
        if (description == null){
            description = "";
        }
        getRepository().merge(new Task(id, name, description, getRepository().getElementById(id).getProjectId(), userId));
    }

    @Override
    public void deleteTasksByProjectId(String projectId) {
        final List<Task> taskList = new ArrayList<>(getRepository().getElements());
        for (Task task : taskList){
            if (task.getProjectId().equals(projectId)){
                getRepository().delete(task.getId());
            }
        }
    }
}