package org.dragard.projectmanager.command;

public class DataLoadCommand extends AbstractCommand {

    public DataLoadCommand() {
        super("load", "Load entities from file", true);
    }

    @Override
    public void execute() throws Exception {
        getServiceLocator().getDomainService().loadSerialization();
    }
}
