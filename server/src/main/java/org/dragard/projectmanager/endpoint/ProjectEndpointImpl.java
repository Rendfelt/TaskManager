package org.dragard.projectmanager.endpoint;

import org.dragard.projectmanager.Bootstrap;
import org.dragard.projectmanager.entity.Project;
import org.dragard.projectmanager.entity.Response;
import org.dragard.projectmanager.entity.User;
import org.dragard.projectmanager.util.UtilClass;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.util.Collection;

@WebService
public class ProjectEndpointImpl {
    
    private Bootstrap bootstrap;

    public ProjectEndpointImpl() {
    }

    public ProjectEndpointImpl(Bootstrap bootstrap) {
        this.bootstrap = bootstrap;
    }

    @WebMethod(operationName = "createProject")
    public @WebResult(name = "response") Response createProject(
            @WebParam(name = "name")String name,
            @WebParam(name = "description")String description,
            @WebParam(name = "token")String token
    ) {
        Response response = new Response();
        try {
            UtilClass.checkToken(token, response);
            User activeUser = bootstrap.getAuthorizationService().getActiveUser();
            Project project = bootstrap.getProjectService().create(name, description, activeUser.getId());
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

    @WebMethod(operationName = "updateProject")
    public Response updateProject(String id, String name, String description, String token) {
        Response response = new Response();
        try {
            UtilClass.checkToken(token, response);
            Project project = bootstrap.getProjectService().update(id, name, description);
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

    @WebMethod(operationName = "deleteProject")
    public Response deleteProject(String id, String token) {
        Response response = new Response();
        try {
            UtilClass.checkToken(token, response);
            Project project = bootstrap.getProjectService().delete(id);
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

    @WebMethod(operationName = "getViewProject")
    public Response getViewProject(String token) {
        Response response = new Response();
        try {
            UtilClass.checkToken(token, response);
            Collection<Project> projects = bootstrap.getProjectService().getElementsByUserId(
                    bootstrap.getAuthorizationService().getActiveUser().getId()
            );
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

    @WebMethod(operationName = "persistProject")
    public Response persistProject(Collection<Project> elements, String token) {
        // TODO: 20.01.2019 Do smthing. or not.
        Response response = new Response();
        /*try {
            UtilClass.checkToken(token, response);
            for (Project project: elements){
                System.out.println(project);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setException(UtilClass.serializeExceptionToByteArray(e));
            return response;
        }*/
        return response;
    }
}
