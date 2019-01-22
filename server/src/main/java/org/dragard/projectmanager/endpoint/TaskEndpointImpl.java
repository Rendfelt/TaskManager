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
public class TaskEndpointImpl extends AbstractEntityEndpoint<Task>
    implements TaskEndpoint {
    
    public TaskEndpointImpl() {
    }

    public TaskEndpointImpl(Bootstrap bootstrap) {
        super(bootstrap, Task.class);
    }

    @Override
    @WebMethod(operationName = "createTask")
    public Response create(@WebParam(name = "name")String name, @WebParam(name = "description")String description, @WebParam(name = "projectId")String projectId, @WebParam(name = "token")String token) {
        Response response = new Response();
        try {
            UtilClass.checkToken(token, response);
            User activeUser = getBootstrap().getAuthorizationService().getActiveUser();
            Task cTask = getBootstrap().getTaskService()
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

    @Override
    @WebMethod(operationName = "updateTask")
    public Response update(String id, String name, String description, String token) {
        Response response = new Response();
        try {
            UtilClass.checkToken(token, response);
            Task task = getBootstrap().getTaskService().update(id, name, description,
                    getBootstrap().getTaskService().getElementById(id).getProjectId());
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
    public Response deleteTask(String id, String token) {

        return super.delete(id, token);

        /*Response response = new Response();
        try {
            UtilClass.checkToken(token, response);
            Task task = getBootstrap().getTaskService().delete(id);
            String message = "Task deleted: \n" + task.toString();
            System.out.println(message);
            response.setMessage(message);
        } catch (Exception e) {
            e.printStackTrace();
            response.setException(UtilClass.serializeExceptionToByteArray(e));
            return response;
        }
        return response;*/
    }

    @Override
    @WebMethod(operationName = "getViewTask")
    public Response getView(String token) {
        Response response = new Response();
        try {
            UtilClass.checkToken(token, response);
            Collection<Task> tasks = getBootstrap().getTaskService().getElementsByUserId(
                    getBootstrap().getAuthorizationService().getActiveUser().getId()
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

    @Override
    @WebMethod(operationName = "persistTask")
    public Response persist(Collection<Task> elements, String token) {
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
