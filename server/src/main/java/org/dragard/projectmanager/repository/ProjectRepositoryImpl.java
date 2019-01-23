package org.dragard.projectmanager.repository;

import org.dragard.projectmanager.api.repository.ProjectRepository;
import org.dragard.projectmanager.entity.Project;
import org.dragard.projectmanager.entity.Task;

import java.util.ArrayList;
import java.util.Collection;

public class ProjectRepositoryImpl extends AbstractRepository<Project>
    implements ProjectRepository {

    @Override
    public Collection<Project> getElementsByUserId(String id) throws Exception {
        Collection<Project> result = new ArrayList<>();
        for (Project element: getElements()){
            if (element.getUserId().equals(id)){
                result.add(element);
            }
        }
        return result;
    }
}
