package org.dragard.projectmanager.server.command;

import java.io.IOException;
import java.net.URISyntaxException;

public class DataSaveCommand extends AbstractCommand {

    public DataSaveCommand() {
        super("save", "Save entities to file", true);
    }

    @Override
    public void execute() throws IOException, URISyntaxException {
        getServiceLocator().getDomainService().saveSerialization();
    }
}
