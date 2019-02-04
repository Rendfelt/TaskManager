package org.dragard.projectmanager.command;

public class DataSaveCommand extends AbstractCommand {

    public DataSaveCommand() {
        super("save", "Save entities to file", true);
    }

    @Override
    public void execute() {
        System.out.println("Operation not supported");
    }
}
