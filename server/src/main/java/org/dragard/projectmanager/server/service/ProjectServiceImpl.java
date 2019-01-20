package org.dragard.projectmanager.server.service;

import org.dragard.projectmanager.server.api.repository.Repository;
import org.dragard.projectmanager.server.api.service.ProjectService;
import org.dragard.projectmanager.server.entity.Project;
import org.dragard.projectmanager.server.exception.NoElementWithIdException;
import org.dragard.projectmanager.server.exception.NoNameException;

import java.util.UUID;

public class ProjectServiceImpl extends AbstractJobEntityService<Project>
    implements ProjectService {

    public ProjectServiceImpl(Repository<Project> repository) {
        super(repository);
    }

    @Override
    public Project create(String name, String description, String userId) throws NoNameException {
        if (name == null || name.isEmpty()){
            throw new NoNameException();
        }
        if (description == null){
            description = "";
        }
        return getRepository().merge(new Project(UUID.randomUUID().toString(), name, description, userId));
    }

    @Override
    public Project update(String id, String name, String description) throws NoNameException, NoElementWithIdException {
        if (getRepository().getElementById(id) == null){
            throw new NoElementWithIdException();
        }
        Project project = getRepository().getElementById(id);
        if (name == null || name.isEmpty()){
            throw new NoNameException();
        }
        if (description == null){
            description = "";
        }
        return getRepository().merge(new Project(id, name, description, project.getUserId()));
    }

}
