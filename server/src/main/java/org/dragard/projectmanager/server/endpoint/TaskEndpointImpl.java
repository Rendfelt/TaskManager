package org.dragard.projectmanager.server.endpoint;

import org.dragard.projectmanager.server.Bootstrap;
import org.dragard.projectmanager.server.entity.Response;
import org.dragard.projectmanager.server.entity.Task;
import org.dragard.projectmanager.server.entity.User;
import org.dragard.projectmanager.server.util.UtilClass;

import javax.jws.WebService;
import java.util.Collection;

@WebService
public class TaskEndpointImpl{
    
    private Bootstrap bootstrap;

    public TaskEndpointImpl() {
    }

    public TaskEndpointImpl(Bootstrap bootstrap) {
        this.bootstrap = bootstrap;
    }

    
    public Response createTask(String name, String description, String projectId, String token) {
        Response response = new Response();
        try {
            UtilClass.checkToken(token, response);
            User activeUser = bootstrap.getAuthorizationService().getActiveUser();
            Task cTask = bootstrap.getTaskService()
                    .create(name, description, projectId, activeUser.getId());
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

    public Response updateTask(String id, String name, String description, String token) {
        Response response = new Response();
        try {
            UtilClass.checkToken(token, response);
            Task task = bootstrap.getTaskService().update(id, name, description,
                    bootstrap.getTaskService().getElementById(id).getProjectId());
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

    
    public Response deleteTask(String id, String token) {
        Response response = new Response();
        try {
            UtilClass.checkToken(token, response);
            Task task = bootstrap.getTaskService().delete(id);
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

    
    public Response getViewTask(String token) {
        Response response = new Response();
        try {
            UtilClass.checkToken(token, response);
            Collection<Task> tasks = bootstrap.getTaskService().getElements();
            StringBuilder sb = new StringBuilder(String.format("\n%-40s%-40s%-40s%-100s\n", "uid", "projectId", "name", "description"));
            for (Task task: tasks){
                sb.append(String.format("%-40s%-40s%-40s%-100s\n", task.getId(), task.getProjectId(), task.getName(), task.getDescription()));
            }
            response.setMessage(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
            response.setException(UtilClass.serializeExceptionToByteArray(e));
            return response;
        }
        return response;
    }
    
    public Response persistTask(Collection<Task> elements, String token) {
        Response response = new Response();
        try {
            UtilClass.checkToken(token, response);
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
