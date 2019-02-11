package org.dragard.projectmanager.repository;

import org.dragard.projectmanager.api.repository.JobRepository;
import org.dragard.projectmanager.entity.Task;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JobRepository<Task> {

    List<Task> findByProject_id(String id);
}
