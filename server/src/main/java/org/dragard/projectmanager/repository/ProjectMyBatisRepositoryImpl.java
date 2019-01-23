package org.dragard.projectmanager.repository;

import org.apache.ibatis.session.SqlSession;
import org.dragard.projectmanager.api.mybatis.mapper.ProjectMapper;
import org.dragard.projectmanager.api.mybatis.mapper.TaskMapper;
import org.dragard.projectmanager.api.repository.ProjectRepository;
import org.dragard.projectmanager.entity.Project;
import org.dragard.projectmanager.entity.Task;

import java.util.Collection;

public class ProjectMyBatisRepositoryImpl extends AbstractMybatisRepository<Project>
    implements ProjectRepository {

    public ProjectMyBatisRepositoryImpl() {
        super(ProjectMapper.class);
    }

    @Override
    public Collection<Project> getElementsByUserId(String id) throws Exception {
        final SqlSession session = getSessionFactory().openSession();
        final ProjectMapper tm = session.getMapper(ProjectMapper.class);
        final Collection<Project> result = tm.getByUserId(id);
        session.commit();
        session.close();
        return result;
    }
}
