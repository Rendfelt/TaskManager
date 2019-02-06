package org.dragard.projectmanager.api.repository;


import org.apache.deltaspike.data.api.FullEntityRepository;
import org.apache.deltaspike.data.api.Repository;
import org.dragard.projectmanager.entity.Task;

@Repository
public interface TaskDSRepository extends FullEntityRepository<Task, String> {

}
