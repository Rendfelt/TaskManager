package org.dragard.projectmanager.service;

import org.dragard.projectmanager.api.repository.Repository;
import org.dragard.projectmanager.api.service.ProjectService;
import org.dragard.projectmanager.entity.Project;
import org.dragard.projectmanager.exception.NoElementWithIdException;
import org.dragard.projectmanager.exception.NoNameException;

import java.util.UUID;

public class ProjectServiceImpl extends AbstractJobEntityService<Project>
    implements ProjectService {

    public ProjectServiceImpl(Repository<Project> repository) {
        super(repository);
    }

    @Override
    public Project create(String name, String description, String userId) throws Exception {
        if (name == null || name.isEmpty()){
            throw new Exception("Name is empty");
        }
        if (description == null){
            description = "";
        }
        return getRepository().merge(new Project(UUID.randomUUID().toString(), name, description, userId));
    }

    @Override
    public Project update(String id, String name, String description) throws Exception {
        if (getRepository().getElementById(id) == null){
            throw new Exception("No element with id");
        }
        Project project = null;
        project = getRepository().getElementById(id);
        if (name == null || name.isEmpty()){
            throw new Exception("Name is empty");
        }
        if (description == null){
            description = "";
        }
        return getRepository().merge(new Project(id, name, description, project.getUserId()));
    }

}
