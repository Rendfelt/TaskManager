package org.dragard.projectmanager.api.event;

import javax.enterprise.event.Event;
import javax.enterprise.event.NotificationOptions;
import javax.enterprise.util.TypeLiteral;
import java.lang.annotation.Annotation;
import java.util.concurrent.CompletionStage;

public interface ProjectManagerEvent<T extends ProjectManagerEvent> extends Event<T> {

    @Override
    void fire(T event);

    @Override
    default <U extends T> CompletionStage<U> fireAsync(U event){
        throw new UnsupportedOperationException();
    }

    @Override
    default <U extends T> CompletionStage<U> fireAsync(U event, NotificationOptions options){
        throw new UnsupportedOperationException();
    }

    @Override
    default Event<T> select(Annotation... qualifiers){
        throw new UnsupportedOperationException();
    }

    @Override
    default <U extends T> Event<U> select(Class<U> subtype, Annotation... qualifiers){
        throw new UnsupportedOperationException();
    }

    @Override
    default <U extends T> Event<U> select(TypeLiteral<U> subtype, Annotation... qualifiers){
        throw new UnsupportedOperationException();
    }
}
