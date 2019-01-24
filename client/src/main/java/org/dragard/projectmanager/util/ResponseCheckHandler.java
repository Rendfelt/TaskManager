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
        Object o = method.invoke(target, args);
        if (!(o instanceof Response)){
            return o;
        }
        Response response = (Response) o;
        UtilClass.checkResponse(response);
        return response;
    }
}
