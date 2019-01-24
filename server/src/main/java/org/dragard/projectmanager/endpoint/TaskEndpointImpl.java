package org.dragard.projectmanager.endpoint;

import org.dragard.projectmanager.Bootstrap;
import org.dragard.projectmanager.api.endpoint.TaskEndpoint;
import org.dragard.projectmanager.entity.Response;
import org.dragard.projectmanager.entity.Task;
import org.dragard.projectmanager.entity.User;
import org.dragard.projectmanager.util.UtilClass;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.Collection;

@WebService
public class TaskEndpointImpl{
    
    public TaskEndpointImpl() {
    }

    private Bootstrap bootstrap;

    public TaskEndpointImpl(Bootstrap bootstrap) {
        this.bootstrap = bootstrap;
    }

    @WebMethod(operationName = "createTask")
    public Response createTask(
            @WebParam(name = "name") String name,
            @WebParam(name = "description") String description,
            @WebParam(name = "projectId") String projectId,
            @WebParam(name = "token") String token
    ) {
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

    @WebMethod(operationName = "updateTask")
    public Response updateTask(
            @WebParam(name = "id") String id,
            @WebParam(name = "name") String name,
            @WebParam(name = "description") String description,
            @WebParam(name = "token") String token
    ) {
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

    @WebMethod(operationName = "deleteTask")
    public Response deleteTask(
            @WebParam(name = "id") String id,
            @WebParam(name = "token") String token
    ) {
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

    @WebMethod(operationName = "getViewTask")
    public Response getViewTask(
            @WebParam(name = "token") String token
    ) {
        Response response = new Response();
        try {
            UtilClass.checkToken(token, response);
            Collection<Task> tasks = bootstrap.getTaskService().getElementsByUserId(
                    bootstrap.getAuthorizationService().getActiveUser().getId()
            );
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

    @WebMethod(operationName = "persistTask")
    public Response persistTask(
            @WebParam(name = "elements") Collection<Task> elements,
            @WebParam(name = "token") String token
    ) {
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
