package org.dragard.projectmanager.service;

import javafx.beans.NamedArg;
import org.dragard.projectmanager.api.annotation.NotEmpty;
import org.dragard.projectmanager.api.annotation.NullAndEmptyChecker;
import org.dragard.projectmanager.api.repository.JobRepository;
import org.dragard.projectmanager.api.service.JobEntityService;
import org.dragard.projectmanager.entity.AbstractJobEntity;
import org.jetbrains.annotations.Nullable;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Transactional
@NullAndEmptyChecker
public abstract class AbstractJobEntityService<E extends AbstractJobEntity> extends AbstractEntityService<E>
    implements JobEntityService<E> {

    @Override
    protected abstract JobRepository<E> getRepository();

    @Override
    public List<E> getElementsByUserId(
            @NamedArg(value = "userId") @Nullable @NotEmpty String userId
    ) throws Exception {
        if (userId == null || userId.isEmpty()){
            throw new Exception("Bad user id");
        }
        Collection<E> elements = getRepository().findByUser_id(userId);
        if (elements == null || elements.isEmpty()){
            throw new Exception("No element by this userId");
        }
        return new ArrayList<>(elements);
    }
}
