package org.dragard.projectmanager.util;


import javafx.beans.NamedArg;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.dragard.projectmanager.api.service.AuthorizationService;
import org.dragard.projectmanager.entity.User;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@NoArgsConstructor
@Getter
@Setter
public class EndpointServiceHandler
    implements InvocationHandler {

    private Object target;
    private AuthorizationService authorizationService;

    public EndpointServiceHandler(Object target, AuthorizationService authorizationService) {
        this.target = target;
        this.authorizationService = authorizationService;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Annotation[][] annotations = method.getParameterAnnotations();
        for (int i = 0; i < annotations.length; i++) {
            System.out.println(annotations[i].getClass());
            System.out.println(annotations[i].length);
            for (int j = 0; j < annotations[i].length; j++) {
                Annotation annotation = annotations[i][j];
                if (annotation instanceof NamedArg){
                    NamedArg namedArgAnnotation = (NamedArg) annotation;
                    if ("token".equals(namedArgAnnotation.value())){
                        try {
                            authorizationService.checkToken(namedArgAnnotation.value());
                        } catch (IOException e) {
                            throw new RuntimeException("Access denied");
                        }
                    }
                }


            }

        }
        System.out.println("TOKEN APPROVED");
        return method.invoke(target, args);
    }
}
