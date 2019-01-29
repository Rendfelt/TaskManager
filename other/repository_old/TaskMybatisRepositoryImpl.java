package org.dragard.projectmanager.repository_old;

import org.dragard.projectmanager.api.mybatis.mapper.TaskMapper;
import org.dragard.projectmanager.api.repository.TaskRepository;
import org.dragard.projectmanager.entity.Task;

public class TaskMybatisRepositoryImpl extends AbstractJobMybatisRepository<Task>
    implements TaskRepository {

    public TaskMybatisRepositoryImpl() {
        super(TaskMapper.class);
    }
}
