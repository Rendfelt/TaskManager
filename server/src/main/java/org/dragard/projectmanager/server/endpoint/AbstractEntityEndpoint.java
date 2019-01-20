package org.dragard.projectmanager.server.endpoint;

import org.dragard.projectmanager.server.api.endpoint.EntityEndpoint;
import org.dragard.projectmanager.server.entity.AbstractEntity;
import org.dragard.projectmanager.server.entity.Project;
import org.dragard.projectmanager.server.entity.Response;
import org.dragard.projectmanager.server.util.UtilClass;

public abstract class AbstractEntityEndpoint<E extends AbstractEntity> extends AbstractEndpoint
    implements EntityEndpoint<E> {

    @Override
    public Response delete(String id, String token) {
        Response response = new Response();
        try {
            UtilClass.checkToken(token, response);
            Project element = getBootstrap().getProjectService().delete(id);
            String message = "Project deleted: \n" + element.toString();
            System.out.println(message);
            response.setMessage(message);
        } catch (Exception e) {
            e.printStackTrace();
            response.setException(UtilClass.serializeExceptionToByteArray(e));
            return response;
        }
        return response;
    }

}
