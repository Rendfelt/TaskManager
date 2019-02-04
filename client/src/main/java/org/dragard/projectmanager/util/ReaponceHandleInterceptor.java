package org.dragard.projectmanager.util;

import org.dragard.projectmanager.api.annotation.ResponceHandle;
import org.dragard.projectmanager.endpoint.Response;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@ResponceHandle
public class ReaponceHandleInterceptor {

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
            System.out.println(String.format("FAILED (%s)", cause));
        }
        return response;
    }
}
