package org.dragard.projectmanager.endpoint.service;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.dragard.projectmanager.Bootstrap;
import org.dragard.projectmanager.api.ServiceLocator;
import org.dragard.projectmanager.api.endpoint.service.TaskEndpointService;
import org.dragard.projectmanager.entity.Response;
import org.dragard.projectmanager.entity.Task;
import org.dragard.projectmanager.entity.User;
import org.dragard.projectmanager.util.UtilClass;

import java.util.Collection;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TaskEndpointServiceImpl
    implements TaskEndpointService {

    @Getter
    @Setter
    private Bootstrap serviceLocator;

    public TaskEndpointServiceImpl(Bootstrap serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    @Override
    public Response create(String name, String description, String projectId, String token) {
        Response response = new Response();
        try {
            String activeUserId = serviceLocator.getAuthorizationService().checkToken(token);
            User activeUser = serviceLocator.getUserService().getElementById(activeUserId);
            Task cTask = serviceLocator.getTaskService().create(name, description, projectId, activeUser.getId());
            String message = "Task created: \n" + cTask.toString();
            System.out.println(message);
            response.setMessage(message);
        } catch (Exception e) {
            e.printStackTrace();
            response.setException(UtilClass.serializeExceptionToByteArray(e));
            return response;
        }
        return response;
    }

    @Override
    public Response update(String id, String name, String description, String token) {
        Response response = new Response();
        try {
            String activeUserId = serviceLocator.getAuthorizationService().checkToken(token);
            User activeUser = serviceLocator.getUserService().getElementById(activeUserId);
            Task task = serviceLocator.getTaskService().update(id, name, description, activeUser.getId());
            String message = "Task updated: \n" + task.toString();
            System.out.println(message);
            response.setMessage(message);
        } catch (Exception e) {
            e.printStackTrace();
            response.setException(UtilClass.serializeExceptionToByteArray(e));
            return response;
        }
        return response;
    }

    @Override
    public Response delete(String id, String token) {
        Response response = new Response();
        try {
            serviceLocator.getAuthorizationService().checkToken(token);
            Task task = serviceLocator.getTaskService().delete(id);
            String message = "Task deleted: \n" + task.toString();
            System.out.println(message);
            response.setMessage(message);
        } catch (Exception e) {
            e.printStackTrace();
            response.setException(UtilClass.serializeExceptionToByteArray(e));
            return response;
        }
        return response;
    }

    @Override
    public Response getView(String token) {
        Response response = new Response();
        try {
            String activeUserId = serviceLocator.getAuthorizationService().checkToken(token);
            User activeUser = serviceLocator.getUserService().getElementById(activeUserId);
            Collection<Task> tasks = serviceLocator.getTaskService().getElementsByUserId(activeUser.getId());
            StringBuilder sb = new StringBuilder(String.format("\n%-40s%-40s%-40s%-100s\n", "uid", "projectId", "name", "description"));
            for (Task task: tasks){
                sb.append(String.format("%-40s%-40s%-40s%-100s\n", task.getId(), task.getProject().getId(), task.getName(), task.getDescription()));
            }
            response.setMessage(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
            response.setException(UtilClass.serializeExceptionToByteArray(e));
            return response;
        }
        return response;
    }

    @Override
    public Response persist(Collection<Task> elements, String token) {
        Response response = new Response();
        try {
            serviceLocator.getAuthorizationService().checkToken(token);
            for (Task task: elements){
                System.out.println(task);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setException(UtilClass.serializeExceptionToByteArray(e));
            return response;
        }
        return response;
    }
}
