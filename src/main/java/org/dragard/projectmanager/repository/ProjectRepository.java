package org.dragard.projectmanager.repository;

import org.dragard.projectmanager.Application;
import org.dragard.projectmanager.entity.Project;
import org.dragard.projectmanager.entity.Task;
import org.dragard.projectmanager.exception.NoActiveElementException;

public class ProjectRepository extends org.dragard.projectmanager.repository.AbstractRepository<Project> {

    public ProjectRepository(Application application) {
        super(application);
    }

    @Override
    public Project findById(long id) {
        return null;
    }

    @Override
    public void create(String name, String description) {
        create(getNewId(), name, description);
    }

    @Override
    public void delete() throws NoActiveElementException {
        super.delete();
        TaskRepository taskRepository = getApplication().getTaskRepository();

    }

    @Override
    public void create(long id, String name, String description) {
        getElements().put(id, new Project(id, name, description));
        setActive(id);
        getApplication().getTaskRepository().clearActive();
    }
}
