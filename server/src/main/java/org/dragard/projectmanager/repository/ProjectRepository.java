package org.dragard.projectmanager.repository;

import org.dragard.projectmanager.api.repository.JobRepository;
import org.dragard.projectmanager.entity.Project;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JobRepository<Project> {


}
