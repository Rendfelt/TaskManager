package org.dragard.projectmanager.command;

import org.dragard.projectmanager.Application;
import org.dragard.projectmanager.exception.NoActiveElementException;

public class DeleteTaskCommand extends AbstractCommand{

    public DeleteTaskCommand(Application application) {
        super("delete_task", "Delete active task", application);
    }

    @Override
    public void execute() {
        try {
            getApplication().getTaskRepository().delete();
            getApplication().getTaskRepository().clearActive();
        } catch (NoActiveElementException e) {
            System.out.println(Application.NO_ACTIVE_PROJECT_MESSAGE);
        }
    }
}
