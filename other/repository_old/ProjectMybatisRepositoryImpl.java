package org.dragard.projectmanager.repository_old;

import org.dragard.projectmanager.api.mybatis.mapper.ProjectMapper;
import org.dragard.projectmanager.api.repository.ProjectRepository;
import org.dragard.projectmanager.entity.Project;

public class ProjectMybatisRepositoryImpl extends AbstractJobMybatisRepository<Project>
    implements ProjectRepository {

    public ProjectMybatisRepositoryImpl() {
        super(ProjectMapper.class);
    }

}
