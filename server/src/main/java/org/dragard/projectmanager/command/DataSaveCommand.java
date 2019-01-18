package org.dragard.projectmanager.command;

import org.dragard.projectmanager.service.DomainServiceImpl;

import java.io.IOException;
import java.net.URISyntaxException;

public class DataSaveCommand extends AbstractCommand {

    public DataSaveCommand() {
        super("save", "Save entities to file", true);
    }

    @Override
    public void execute() throws IOException, URISyntaxException {
        new DomainServiceImpl(getServiceLocator()).saveSerialization();
    }
}
