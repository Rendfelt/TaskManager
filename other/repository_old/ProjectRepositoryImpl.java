package org.dragard.projectmanager.repository_old;

import org.dragard.projectmanager.api.repository.ProjectRepository;
import org.dragard.projectmanager.entity.Project;

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
