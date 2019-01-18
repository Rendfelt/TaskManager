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
    public void create(String name, String description, String userId) throws NoNameException {
        if (name == null || name.isEmpty()){
            throw new NoNameException();
        }
        if (description == null){
            description = "";
        }

        getRepository().merge(new Project(UUID.randomUUID().toString(), name, description, userId));
    }

    @Override
    public void update(String id, String name, String description, String userId) throws NoNameException, NoElementWithIdException {
        if (getRepository().getElementById(id) == null){
            throw new NoElementWithIdException();
        }
        if (name == null || name.isEmpty()){
            throw new NoNameException();
        }
        if (description == null){
            description = "";
        }
        getRepository().merge(new Project(id, name, description, userId));
    }

}