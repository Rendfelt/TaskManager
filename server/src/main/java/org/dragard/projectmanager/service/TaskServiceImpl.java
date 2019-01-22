package org.dragard.projectmanager.service;

import org.dragard.projectmanager.api.repository.TaskRepository;
import org.dragard.projectmanager.api.service.TaskService;
import org.dragard.projectmanager.entity.Task;
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
    public Task create(String name, String description, String projectId, String userId) throws Exception {
        if (name == null || name.isEmpty()){
            throw new Exception("Name is empty");
        }
        if (description == null){
            description = "";
        }
        return getRepository().merge(new Task(UUID.randomUUID().toString(), name, description, projectId, userId));
    }

    @Override
    public Task update(String id, String name, String description, String userId) throws Exception {
        if (getRepository().getElementById(id) == null){
            throw new Exception("No element with id");
        }
        if (name == null || name.isEmpty()){
            throw new Exception("Name is empty");
        }
        if (description == null){
            description = "";
        }
        return getRepository().merge(new Task(id, name, description, getRepository().getElementById(id).getProjectId(), userId));
    }

    @Override
    public void deleteTasksByProjectId(String projectId) throws Exception {
        final List<Task> taskList;
        taskList = new ArrayList<>(getRepository().getElements());
        for (Task task : taskList){
            if (task.getProjectId().equals(projectId)){
                getRepository().delete(task.getId());
            }
        }
    }
}
