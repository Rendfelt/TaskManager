package org.dragard.projectmanager.service;

import org.dragard.projectmanager.api.repository.JobRepository;
import org.dragard.projectmanager.api.service.JobEntityService;
import org.dragard.projectmanager.entity.AbstractJobEntity;
import org.dragard.projectmanager.util.HibernateUtils;
import javax.persistence.EntityManager;
import java.util.List;

public abstract class AbstractJobEntityService<E extends AbstractJobEntity> extends AbstractEntityService<E>
    implements JobEntityService<E> {

    @Override
    protected abstract JobRepository<E> getRepository();

    @Override
    public List<E> getElementsByUserId(String userId) throws Exception {
        if (userId == null || userId.isEmpty()){
            throw new Exception("Bad user id");
        }
        EntityManager entityManager = HibernateUtils.getSession();
        entityManager.getTransaction().begin();

        List<E> elements = getRepository().getElementsByUserId(userId, entityManager);
        if (elements == null || elements.isEmpty()){
            throw new Exception("No element by this userId");
        }

        entityManager.getTransaction().commit();
        entityManager.close();

        return elements;
    }
}
