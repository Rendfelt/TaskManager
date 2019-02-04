package org.dragard.projectmanager.util;

import lombok.AccessLevel;
import lombok.Setter;
import org.dragard.projectmanager.api.ServiceLocator;
import org.dragard.projectmanager.api.annotation.ResponceHandle;
import org.dragard.projectmanager.endpoint.Response;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@ResponceHandle
public class ReaponceHandleInterceptor {

    @Inject
    @Setter(value = AccessLevel.PROTECTED)
    private ServiceLocator serviceLocator;

    @AroundInvoke
    public Object handleResponce(InvocationContext ctx) throws Exception{
        Response response = null;
        try {
            final Object o = ctx.proceed();
            if (!(o instanceof Response)){
                return o;
            }
            response = (Response) o;
            UtilClass.checkResponse(response);
        } catch (Exception e) {
            String cause = e.getMessage();
            while (e.getCause() != null){
                cause = cause + " " + e.getCause().getMessage();
                e = (Exception) e.getCause();
            }
            if (cause.contains("Invalid token") || cause.contains("Token corrupted")){
                serviceLocator.getAuthorizationService().setToken(null);
            }
            System.out.println(String.format("FAILED (%s)", cause));
        }
        return response;
    }
}
