package org.dragard.projectmanager.service;

import org.dragard.projectmanager.api.repository.Repository;
import org.dragard.projectmanager.api.service.EntityService;
import org.dragard.projectmanager.entity.AbstractEntity;
import org.dragard.projectmanager.util.HibernateUtils;

import javax.persistence.EntityManager;

public abstract class AbstractEntityService<E extends AbstractEntity>
    implements EntityService<E> {

    protected abstract Repository<E> getRepository();

    @Override
    public E getElementById(String id) {
        EntityManager entityManager = HibernateUtils.getSession();
        entityManager.getTransaction().begin();

        E element = getRepository().getElementById(id, entityManager);

        entityManager.getTransaction().commit();
        entityManager.close();
        return element;
    }

    @Override
    public E delete(String id){
        EntityManager entityManager = HibernateUtils.getSession();
        entityManager.getTransaction().begin();

        if (getRepository().getElementById(id, entityManager) == null){
            throw new RuntimeException("No element deleted");
        }
        E element = getRepository().delete(id, entityManager);

        entityManager.getTransaction().commit();
        entityManager.close();
        return element;
    }

/*    @Override
    public void persist(Collection<E> elements) throws Exception {
        if (elements == null || elements.isEmpty()){
            return;
        }
        for (E element :
                elements) {
            if (getElementById(element.getId()) == null){
                getRepository().merge(element);
            }
        }
    }*/
}
