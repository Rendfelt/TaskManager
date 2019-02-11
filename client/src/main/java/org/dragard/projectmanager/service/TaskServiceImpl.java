package org.dragard.projectmanager.service;

import org.dragard.projectmanager.api.annotation.ResponceHandle;
import org.dragard.projectmanager.api.service.TaskService;
import org.dragard.projectmanager.endpoint.Response;
import org.dragard.projectmanager.endpoint.TaskEndpointImplService;
import org.springframework.stereotype.Component;


//@ResponceHandle
@Component
public class TaskServiceImpl
    implements TaskService {

    private TaskEndpointImplService taskEndpoint;

    protected TaskServiceImpl() {
        this.taskEndpoint = new TaskEndpointImplService();
    }

    public TaskEndpointImplService getTaskEndpoint() {
        return taskEndpoint;
    }

    @Override
    public Response create(String name, String description, String projectId, String token) {
        return getTaskEndpoint().getTaskEndpointImplPort().createTask(name, description, projectId, token);
    }

    @Override
    public Response update(String id, String name, String description, String token) {
        return getTaskEndpoint().getTaskEndpointImplPort().updateTask(id, name, description, token);
    }

    @Override
    public Response delete(String id, String token) {
        return getTaskEndpoint().getTaskEndpointImplPort().deleteTask(id, token);
    }

    @Override
    public Response getView(String token) {
        return getTaskEndpoint().getTaskEndpointImplPort().getViewTask(token);
    }

}
