package org.dragard.projectmanager.repository;

import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.Repository;
import org.dragard.projectmanager.api.annotation.Preferred;
import org.dragard.projectmanager.api.repository.JobRepository;
import org.dragard.projectmanager.entity.Project;

import javax.enterprise.context.ApplicationScoped;

@Repository
@ApplicationScoped
@Preferred
public interface ProjectDSRepository extends JobRepository<Project> {


}
