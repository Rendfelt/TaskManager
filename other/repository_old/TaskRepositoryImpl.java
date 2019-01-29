package org.dragard.projectmanager.repository_old;


import org.dragard.projectmanager.api.repository.TaskRepository;
import org.dragard.projectmanager.entity.Task;

import java.util.ArrayList;
import java.util.Collection;

public class TaskRepositoryImpl extends AbstractRepository<Task>
    implements TaskRepository {

    @Override
    public Collection<Task> getElementsByUserId(String id) throws Exception {
        Collection<Task> result = new ArrayList<>();
        for (Task element: getElements()){
            if (element.getUserId().equals(id)){
                result.add(element);
            }
        }
        return result;
    }
}
