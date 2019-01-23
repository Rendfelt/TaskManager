package org.dragard.projectmanager.repository;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.dragard.projectmanager.api.mybatis.mapper.TaskMapper;
import org.dragard.projectmanager.api.repository.TaskRepository;
import org.dragard.projectmanager.entity.Task;
import org.dragard.projectmanager.util.UtilClass;

import java.util.Collection;

public class TaskMybatisRepositoryImpl
    implements TaskRepository {

    private final SqlSessionFactory sessionFactory = UtilClass.getSqlSessionFactory();

    @Override
    public Task getElementById(String id) throws Exception {
        final SqlSession session = sessionFactory.openSession();
        final TaskMapper tm = session.getMapper(TaskMapper.class);
        final Task result = tm.getById(id);
        session.commit();
        session.close();
        return result;
    }

    @Override
    public Task merge(Task element) throws Exception {
        final SqlSession session = sessionFactory.openSession();
        final TaskMapper tm = session.getMapper(TaskMapper.class);
        final Task task = getElementById(element.getId());
        if (task == null){
            tm.insert(element);
        } else {
            tm.update(element);
        }
        session.commit();
        session.close();
        return element;
    }

    @Override
    public void clearElements() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<Task> getElements() throws Exception {
        final SqlSession session = sessionFactory.openSession();
        final TaskMapper tm = session.getMapper(TaskMapper.class);
        final Collection<Task> result = tm.getAll();
        session.commit();
        session.close();
        return result;
    }

    @Override
    public Collection<Task> getElementsByUserId(String id) throws Exception {
        final SqlSession session = sessionFactory.openSession();
        final TaskMapper tm = session.getMapper(TaskMapper.class);
        final Collection<Task> result = tm.getByUserId(id);
        session.commit();
        session.close();
        return result;
    }

    @Override
    public Task delete(String id) throws Exception {
        final SqlSession session = sessionFactory.openSession();
        final TaskMapper tm = session.getMapper(TaskMapper.class);
        final Task result = tm.getById(id);
        tm.delete(id);
        session.commit();
        session.close();
        return result;
    }
}
