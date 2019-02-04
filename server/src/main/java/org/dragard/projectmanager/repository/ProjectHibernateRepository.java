package org.dragard.projectmanager.repository;

import org.dragard.projectmanager.api.repository.ProjectRepository;
import org.dragard.projectmanager.entity.Project;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProjectHibernateRepository extends AbstractJobEntityHibernateRepository<Project>
    implements ProjectRepository {

    public ProjectHibernateRepository() {
        super(Project.class);
    }

}
