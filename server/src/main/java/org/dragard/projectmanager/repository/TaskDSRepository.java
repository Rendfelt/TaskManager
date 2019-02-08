package org.dragard.projectmanager.repository;

import org.apache.deltaspike.data.api.Repository;
import org.dragard.projectmanager.api.annotation.Preferred;
import org.dragard.projectmanager.api.repository.JobRepository;
import org.dragard.projectmanager.entity.Task;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@Repository
@ApplicationScoped
@Preferred
public interface TaskDSRepository extends JobRepository<Task> {

    List<Task> findByProject_id(String id);
}
