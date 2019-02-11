package org.dragard.projectmanager.service;

import javafx.beans.NamedArg;
import org.dragard.projectmanager.api.annotation.NotEmpty;
import org.dragard.projectmanager.api.annotation.NullAndEmptyChecker;
import org.dragard.projectmanager.api.repository.IRepository;
import org.dragard.projectmanager.api.service.EntityService;
import org.dragard.projectmanager.entity.AbstractEntity;
import org.jetbrains.annotations.Nullable;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@NullAndEmptyChecker
public abstract class AbstractEntityService<E extends AbstractEntity>
    implements EntityService<E> {

    protected abstract IRepository<E> getRepository();

    @Override
    public E getElementById(
            @NamedArg(value = "id") @Nullable @NotEmpty String id
    ) {
        return getRepository().findById(id).orElse(null);
    }

    @Override
    public E delete(
            @NamedArg(value = "id") @Nullable @NotEmpty String id
    ){
        E element = getRepository().findById(id).orElse(null);
        if (element == null){
            throw new RuntimeException("No element deleted");
        }
        getRepository().delete(element);
        return element;
    }

}
