package org.dragard.projectmanager.repository;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.dragard.projectmanager.api.mybatis.mapper.EntityMapper;
import org.dragard.projectmanager.api.repository.Repository;
import org.dragard.projectmanager.entity.AbstractEntity;
import org.dragard.projectmanager.util.UtilClass;

import java.util.Collection;

public abstract class AbstractMybatisRepository<E extends AbstractEntity>
    implements Repository<E> {

    private final SqlSessionFactory sessionFactory = UtilClass.getSqlSessionFactory();
    private final Class mapper;

    protected Class getMapper() {
        return mapper;
    }

    protected AbstractMybatisRepository(Class mapper) {
        this.mapper = mapper;
    }

    protected SqlSessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Override
    public void clearElements(){
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<E> getElements() throws Exception {
        final SqlSession session = sessionFactory.openSession();
        final Object o = session.getMapper(mapper);
        final EntityMapper<E> tm = (EntityMapper<E>) o;
        final Collection<E> result = tm.getAll();
        session.commit();
        session.close();
        return result;
    }

    @Override
    public E getElementById(String id) throws Exception {
        final SqlSession session = sessionFactory.openSession();
        final Object o = session.getMapper(mapper);
        final EntityMapper<E> tm = (EntityMapper<E>) o;
        final E result = tm.getById(id);
        session.commit();
        session.close();
        return result;
    }

    @Override
    public E delete(String id) throws Exception {
        final SqlSession session = sessionFactory.openSession();
        final Object o = session.getMapper(mapper);
        final EntityMapper<E> tm = (EntityMapper<E>) o;
        final E result = tm.getById(id);
        tm.delete(id);
        session.commit();
        session.close();
        return result;

    }

    @Override
    public E merge(E element) throws Exception {
        final SqlSession session = sessionFactory.openSession();
        final Object o = session.getMapper(mapper);
        final EntityMapper<E> tm = (EntityMapper<E>) o;
        final E e = getElementById(element.getId());
        if (e == null){
            tm.insert(element);
        } else {
            tm.update(element);
        }
        session.commit();
        session.close();
        return element;
    }

}
