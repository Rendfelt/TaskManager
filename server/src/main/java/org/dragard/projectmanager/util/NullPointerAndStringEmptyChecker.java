package org.dragard.projectmanager.util;

import javafx.beans.NamedArg;
import org.dragard.projectmanager.api.annotation.NotEmpty;
import org.dragard.projectmanager.api.annotation.NullAndEmptyChecker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.lang.annotation.Annotation;

@Interceptor
@NullAndEmptyChecker
public class NullPointerAndStringEmptyChecker {

    @AroundInvoke
    public Object checkNull(InvocationContext ctx) throws Exception{
        final Annotation[][] annotations = ctx.getMethod().getParameterAnnotations();
        boolean nullable;
        boolean notEmpty;
        NamedArg namedArg;
        for (int i = 0; i < annotations.length; i++) {
            nullable = false;
            namedArg = null;
            notEmpty = false;
            for (int j = 0; j < annotations[i].length; j++) {
                Annotation annotation = annotations[i][j];
                if (annotation instanceof NamedArg){
                    namedArg = (NamedArg) annotation;
                } else if (annotation instanceof Nullable){
                    nullable = true;
                } else if (annotation instanceof NotEmpty){
                    notEmpty = true;
                }
            }
            String name = namedArg == null ? null : namedArg.value();
            if (nullable && ctx.getParameters()[i] == null){
                throw new RuntimeException("Parameter " + (name == null ? "arg" + i : name) +
                        " cannot be null (in " + ctx.getTarget().getClass().getSimpleName() + " " +
                        ctx.getMethod().getName() + " method)");
            }
            if (notEmpty && ctx.getParameters()[i].getClass() == String.class){
                String str = (String) ctx.getParameters()[i];
                if (str.isEmpty()){
                    throw new RuntimeException("String parameter " + (name == null ? "arg" + i : name) +
                            " cannot be empty (in " + ctx.getTarget().getClass().getSimpleName() + " " +
                            ctx.getMethod().getName() + " method)");
                }
            }
        }

        return ctx.proceed();
    }
}
