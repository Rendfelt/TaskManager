package org.dragard.projectmanager.util;

import org.dragard.projectmanager.endpoint.Response;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ResponseCheckHandler
    implements InvocationHandler {

    private Object target;

    public ResponseCheckHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Response response = null;
        try {
            Object o = method.invoke(target, args);
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
