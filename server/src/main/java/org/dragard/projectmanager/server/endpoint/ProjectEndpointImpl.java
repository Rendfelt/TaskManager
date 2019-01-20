package org.dragard.projectmanager.server.endpoint;

import org.dragard.projectmanager.server.Bootstrap;
import org.dragard.projectmanager.server.api.endpoint.ProjectEndpoint;
import org.dragard.projectmanager.server.entity.Project;
import org.dragard.projectmanager.server.entity.Response;
import org.dragard.projectmanager.server.entity.User;
import org.dragard.projectmanager.server.util.UtilClass;

import javax.jws.WebService;
import java.util.Collection;

@WebService
public class ProjectEndpointImpl extends AbstractEndpoint
    implements ProjectEndpoint {

    public ProjectEndpointImpl() {
    }

    public ProjectEndpointImpl(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public Response create(String name, String description, String token) {
        Response response = new Response();
        try {
            UtilClass.checkToken(token, response);
            User activeUser = getBootstrap().getAuthorizationService().getActiveUser();
            Project project = getBootstrap().getProjectService().create(name, description, activeUser.getId());
            String message = "Project created: \n" + project.toString();
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
            Project project = getBootstrap().getProjectService().update(id, name, description);
            String message = "Project updated: \n" + project.toString();
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
            Project project = getBootstrap().getProjectService().delete(id);
            String message = "Project deleted: \n" + project.toString();
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
            Collection<Project> projects = getBootstrap().getProjectService().getElements();
            StringBuilder sb = new StringBuilder(String.format("\n%-40s%-40s%-100s\n", "uid", "name", "description"));
            for (Project project: projects){
                sb.append(String.format("%-40s%-40s%-100s\n", project.getId(),project.getName(), project.getDescription()));
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
    public Response persist(Collection<Project> elements, String token) {
        Response response = new Response();
        try {
            UtilClass.checkToken(token, response);
            for (Project project: elements){
                System.out.println(project);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setException(UtilClass.serializeExceptionToByteArray(e));
            return response;
        }
        return response;
    }
}
