package org.dragard.projectmanager.command;

import org.dragard.projectmanager.Application;
import org.dragard.projectmanager.exception.NoActiveElementException;

public class DeleteProjectCommand extends AbstractCommand{

    public DeleteProjectCommand(Application application) {
        super("delete_project", "Delete active project", application);
    }

    @Override
    public void execute() {
        try {
            getApplication().getProjectRepository().delete();
            getApplication().getTaskRepository().removeTaskByProjectId(getApplication().getProjectRepository().getActive().getId());
            getApplication().getTaskRepository().clearActive();
            getApplication().getProjectRepository().clearActive();
        } catch (NoActiveElementException e) {
            System.out.println(Application.NO_ACTIVE_PROJECT_MESSAGE);
        }
    }
}
