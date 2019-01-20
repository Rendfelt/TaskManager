package org.dragard.projectmanager.server.endpoint;

import org.dragard.projectmanager.server.Bootstrap;
import org.dragard.projectmanager.server.api.endpoint.TaskEndpoint;
import org.dragard.projectmanager.server.entity.Response;
import org.dragard.projectmanager.server.entity.Task;
import org.dragard.projectmanager.server.entity.User;
import org.dragard.projectmanager.server.util.UtilClass;

import javax.jws.WebService;
import java.util.Collection;

@WebService
public class TaskEndpointImpl extends AbstractEndpoint
    implements TaskEndpoint {

    public TaskEndpointImpl() {
    }

    public TaskEndpointImpl(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public Response create(String name, String description, String projectId, String token) {
        Response response = new Response();
        try {
            UtilClass.checkToken(token, response);
            User activeUser = getBootstrap().getAuthorizationService().getActiveUser();
            Task task = getBootstrap().getTaskService().create(name, description, projectId, activeUser.getId());
            String message = "Task created: \n" + task.toString();
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

    @Override
    public Response delete(String id, String token) {
        Response response = new Response();
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
        return response;
    }

    @Override
    public Response getView(String token) {
        Response response = new Response();
        try {
            UtilClass.checkToken(token, response);
            Collection<Task> tasks = getBootstrap().getTaskService().getElements();
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
    public Response persist(Collection<Task> elements, String token) {
        return null;
    }
}
