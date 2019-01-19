package org.dragard.projectmanager.server.service;

import org.dragard.projectmanager.server.api.repository.Repository;
import org.dragard.projectmanager.server.api.service.EntityService;
import org.dragard.projectmanager.server.entity.AbstractEntity;

import java.util.Collection;

public abstract class AbstractEntityService<E extends AbstractEntity>
    implements EntityService<E> {

    protected abstract Repository<E> getRepository();

    @Override
    public void clearElements(){
        getRepository().clearElements();
    }

    @Override
    public E getElementById(String id) {
        return getRepository().getElementById(id);
    }

    @Override
    public Collection<E> getElements() {
        return getRepository().getElements();
    }

    @Override
    public void delete(String id) {
        if (getRepository().getElementById(id) == null){
            System.out.println("No element deleted");
            return;
        }
        getRepository().delete(id);
    }

    @Override
    public void persist(Collection<E> elements) {
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
