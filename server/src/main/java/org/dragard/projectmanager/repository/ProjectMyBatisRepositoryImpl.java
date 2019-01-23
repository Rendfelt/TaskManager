package org.dragard.projectmanager.repository;

import org.apache.ibatis.session.SqlSession;
import org.dragard.projectmanager.api.mybatis.mapper.ProjectMapper;
import org.dragard.projectmanager.api.repository.ProjectRepository;
import org.dragard.projectmanager.entity.Project;

import java.util.Collection;

public class ProjectMyBatisRepositoryImpl extends AbstractJobMybatisRepository<Project>
    implements ProjectRepository {

    public ProjectMyBatisRepositoryImpl() {
        super(ProjectMapper.class);
    }

}
