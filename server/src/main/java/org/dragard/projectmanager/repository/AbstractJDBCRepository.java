package org.dragard.projectmanager.repository;

import org.dragard.projectmanager.api.repository.Repository;
import org.dragard.projectmanager.entity.AbstractEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractJDBCRepository<E extends AbstractEntity>
    implements Repository<E> {

    /*protected AbstractJDBCRepository() {
        
    }

    @Override
    public Collection<E> getElements() {
        // TODO: 22.01.2019  
        return null;
    }

    @Override
    public E getElementById(String id) {
        // TODO: 22.01.2019  
        return null;
    }

    @Override
    public void clearElements() {
        throw new UnsupportedOperationException();
    }

    @Override
    public E delete(String id) {
        // TODO: 22.01.2019  
        return null;
    }

    @Override
    public E merge(E element) {
        // TODO: 22.01.2019  
        return null;
    }*/

}
