package org.dragard.projectmanager.service;

import org.dragard.projectmanager.api.service.AuthorizationService;
import org.dragard.projectmanager.api.service.TaskService;
import org.dragard.projectmanager.endpoint.AuthorizationEndpointImplService;
import org.dragard.projectmanager.endpoint.Response;
import org.dragard.projectmanager.endpoint.TaskEndpointImplService;
import org.dragard.projectmanager.util.UtilClass;

public class TaskServiceImpl
    implements TaskService {

    private TaskEndpointImplService taskEndpoint;

    public static TaskService getInstance(TaskEndpointImplService taskEndpointImplService){
        return (TaskService) UtilClass.getServiceProxy(TaskService.class, new TaskServiceImpl(taskEndpointImplService));
    }

    public TaskServiceImpl() {
    }

    private TaskServiceImpl(TaskEndpointImplService taskEndpoint) {
        this.taskEndpoint = taskEndpoint;
    }

    public TaskEndpointImplService getTaskEndpoint() {
        return taskEndpoint;
    }

    public void setTaskEndpoint(TaskEndpointImplService taskEndpoint) {
        this.taskEndpoint = taskEndpoint;
    }

    @Override
    public Response create(String name, String description, String projectId, String token) throws Exception {
        return getTaskEndpoint().getTaskEndpointImplPort().createTask(name, description, projectId, token);
    }

    @Override
    public Response update(String id, String name, String description, String token) throws Exception {
        return getTaskEndpoint().getTaskEndpointImplPort().updateTask(id, name, description, token);
    }

    @Override
    public Response delete(String id, String token) throws Exception {
        return getTaskEndpoint().getTaskEndpointImplPort().deleteTask(id, token);
    }

    @Override
    public Response getView(String token) throws Exception {
        return getTaskEndpoint().getTaskEndpointImplPort().getViewTask(token);
    }

/*    @Override
    public Response persist(List elements, String token) throws Exception {
        Response response = getTaskEndpoint().getTaskEndpointImplPort().persistTask(elements, token);
        UtilClass.checkResponse(response);

        return response;
    }*/
}
