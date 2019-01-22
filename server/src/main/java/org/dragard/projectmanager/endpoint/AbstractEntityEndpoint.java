package org.dragard.projectmanager.endpoint;

import org.dragard.projectmanager.Bootstrap;
import org.dragard.projectmanager.api.endpoint.EntityEndpoint;
import org.dragard.projectmanager.api.service.EntityService;
import org.dragard.projectmanager.entity.AbstractEntity;
import org.dragard.projectmanager.entity.AbstractJobEntity;
import org.dragard.projectmanager.entity.Response;
import org.dragard.projectmanager.entity.User;
import org.dragard.projectmanager.util.UtilClass;

import java.util.Collection;

public abstract class AbstractEntityEndpoint<E extends AbstractJobEntity> extends AbstractEndpoint
    implements EntityEndpoint<E> {

    private Class<E> clazz;

    public AbstractEntityEndpoint() {
    }

    public AbstractEntityEndpoint(Bootstrap bootstrap, Class<E> clazz) {
        super(bootstrap);
        this.clazz = clazz;
    }

    @Override
    public Response delete(String id, String token) {
        Response response = new Response();
        try {
            E task = ((EntityService<E>)Bootstrap.class.getMethod(
                    String.format("get%sService", clazz.getSimpleName())).invoke(getBootstrap())
            ).delete(id);

            UtilClass.checkToken(token, response);
            String message = String.format("%s deleted: %s\n", clazz.getSimpleName(), task.toString());
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
