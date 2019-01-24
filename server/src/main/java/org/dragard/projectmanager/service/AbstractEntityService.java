package org.dragard.projectmanager.service;

import org.dragard.projectmanager.api.repository.Repository;
import org.dragard.projectmanager.api.service.EntityService;
import org.dragard.projectmanager.entity.AbstractEntity;

import java.util.Collection;

public abstract class AbstractEntityService<E extends AbstractEntity>
    implements EntityService<E> {

    protected abstract Repository<E> getRepository();

    @Override
    public void clearElements(){
        getRepository().clearElements();
    }

    @Override
    public E getElementById(String id) throws Exception {
        return getRepository().getElementById(id);
    }

    @Override
    public Collection<E> getElements() throws Exception {
        return getRepository().getElements();
    }

    @Override
    public E delete(String id) throws Exception {
        if (getRepository().getElementById(id) == null){
            System.out.println("No element deleted");
            return null;
        }
        return getRepository().delete(id);
    }

    @Override
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
    }
}
