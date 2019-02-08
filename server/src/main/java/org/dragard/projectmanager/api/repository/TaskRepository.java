package org.dragard.projectmanager.api.repository;

import org.dragard.projectmanager.entity.Task;

import java.util.List;

public interface TaskRepository extends JobRepository<Task>{

    List<Task> getElementsByProjectId(String id);
}
