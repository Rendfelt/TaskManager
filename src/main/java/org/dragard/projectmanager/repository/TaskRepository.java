package org.dragard.projectmanager.repository;

import org.dragard.projectmanager.Application;
import org.dragard.projectmanager.entity.Task;
import org.dragard.projectmanager.exception.NoActiveElementException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TaskRepository extends AbstractRepository<Task>{

    private final Map<Long, Long> idTaskProject;

    @Override
    public void create(long id, String name, String description) {
        getElements().put(id, new Task(id, name, description));
        idTaskProject.put(id, getApplication().getProjectRepository().getActive().getId());
        setActive(id);
    }

    @Override
    public void delete() throws NoActiveElementException {
        super.delete();
        idTaskProject.remove(getActive().getId());
    }

    public void removeTaskByProjectId(final long id){
        Iterator<Map.Entry<Long, Long>> iterator = idTaskProject.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<Long, Long> entry = iterator.next();
            if (entry.getValue() == id){
                long taskId = entry.getKey();
                getElements().remove(taskId);
                iterator.remove();
            }
        }
        for (Map.Entry<Long, Long> entry :
                idTaskProject.entrySet()) {
            if (entry.getValue() == id){
                long taskId = entry.getKey();
                getElements().remove(taskId);
                idTaskProject.remove(taskId);
            }
        }
    }

    public long getProjectIdByTaskId(final long id){
        return idTaskProject.get(id);
    }

    @Override
    public void create(String name, String description) {
        create(getNewId(), name, description);
    }

    public TaskRepository(Application application) {
        super(application);
        idTaskProject = new HashMap<>();
    }
}
