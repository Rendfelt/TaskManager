package org.dragard.projectmanager.repository;

import org.dragard.projectmanager.api.repository.JobRepository;
import org.dragard.projectmanager.entity.AbstractJobEntity;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public abstract class AbstractJobEntityHibernateRepository<E extends AbstractJobEntity> extends AbstractEntityHibernateRepository<E>
    implements JobRepository<E> {

    public AbstractJobEntityHibernateRepository(Class<E> clazz) {
        super(clazz);
    }

    @Override
    public List<E> getElementsByUserId(String id, EntityManager entityManager) throws Exception {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<E> elementCriteria = cb.createQuery(getClazz());
        Root<E> elementRoot = elementCriteria.from(getClazz());
        elementCriteria.select(elementRoot);
        elementCriteria.where(cb.equal(elementRoot.get("user").get("id"), id));
        return entityManager.createQuery(elementCriteria).getResultList();
    }
}
