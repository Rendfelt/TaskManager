package org.dragard.projectmanager.endpoint.service;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.dragard.projectmanager.api.ServiceLocator;
import org.dragard.projectmanager.api.endpoint.service.ProjectEndpointService;
import org.dragard.projectmanager.entity.Project;
import org.dragard.projectmanager.entity.Response;
import org.dragard.projectmanager.entity.User;
import org.dragard.projectmanager.util.UtilClass;
import org.springframework.stereotype.Component;
import javax.inject.Inject;
import java.util.Collection;

@Component
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProjectEndpointServiceImpl
    implements ProjectEndpointService {

    @Getter
    @Setter
    @Inject
    private ServiceLocator serviceLocator;

    @Override
    public Response create(String name, String description, String token) {
        Response response = new Response();
        try {
            String activeUserId = serviceLocator.getAuthorizationService().checkToken(token);
            User activeUser = serviceLocator.getUserService().getElementById(activeUserId);
            Project project = serviceLocator.getProjectService().create(name, description, activeUser.getId());
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
            serviceLocator.getAuthorizationService().checkToken(token);
            Project project = serviceLocator.getProjectService().update(id, name, description);
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
            serviceLocator.getAuthorizationService().checkToken(token);
            Project project = serviceLocator.getProjectService().delete(id);
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
            String activeUserId = serviceLocator.getAuthorizationService().checkToken(token);
            User activeUser = serviceLocator.getUserService().getElementById(activeUserId);
            Collection<Project> projects = serviceLocator.getProjectService().getElementsByUserId(activeUser.getId());
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
