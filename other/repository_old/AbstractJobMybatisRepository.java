package org.dragard.projectmanager.repository_old;

import org.apache.ibatis.session.SqlSession;
import org.dragard.projectmanager.api.mybatis.mapper.JobEntityMapper;
import org.dragard.projectmanager.api.repository.JobRepository;
import org.dragard.projectmanager.entity.AbstractJobEntity;
import java.util.Collection;

public abstract class AbstractJobMybatisRepository<E extends AbstractJobEntity> extends AbstractMybatisRepository<E>
    implements JobRepository<E> {

    protected AbstractJobMybatisRepository(Class mapper) {
        super(mapper);
    }

    @Override
    public Collection<E> getElementsByUserId(String id) throws Exception {
        final SqlSession session = getSessionFactory().openSession();
        final Object o = session.getMapper(getMapper());
        final JobEntityMapper<E> tm = (JobEntityMapper<E>) o;
        final Collection<E> result = tm.getByUserId(id);
        session.commit();
        session.close();
        return result;
    }
}
