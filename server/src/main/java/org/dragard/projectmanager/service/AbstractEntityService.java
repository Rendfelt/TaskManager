package org.dragard.projectmanager.service;

import javafx.beans.NamedArg;
import org.apache.deltaspike.data.api.FullEntityRepository;
import org.dragard.projectmanager.api.annotation.NotEmpty;
import org.dragard.projectmanager.api.annotation.NullAndEmptyChecker;
import org.dragard.projectmanager.api.repository.IRepository;
import org.dragard.projectmanager.api.service.EntityService;
import org.dragard.projectmanager.entity.AbstractEntity;
import org.dragard.projectmanager.util.HibernateUtils;
import org.jetbrains.annotations.Nullable;

import javax.persistence.EntityManager;

@NullAndEmptyChecker
public abstract class AbstractEntityService<E extends AbstractEntity>
    implements EntityService<E> {

    protected abstract IRepository<E> getRepository();

    @Override
    public E getElementById(
            @NamedArg(value = "id") @Nullable @NotEmpty String id
    ) {
        return getRepository().findBy(id);
    }

    @Override
    public E delete(
            @NamedArg(value = "id") @Nullable @NotEmpty String id
    ){
        EntityManager entityManager = HibernateUtils.getSession();
        entityManager.getTransaction().begin();
        E element = getRepository().findBy(id);
        if (element == null){
            throw new RuntimeException("No element deleted");
        }
        getRepository().remove(element);

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
