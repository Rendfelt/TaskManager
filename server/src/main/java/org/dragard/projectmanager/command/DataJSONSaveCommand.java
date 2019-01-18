package org.dragard.projectmanager.command;

import org.dragard.projectmanager.service.DomainServiceImpl;

import java.io.IOException;
import java.net.URISyntaxException;

public class DataJSONSaveCommand extends AbstractCommand {

    public DataJSONSaveCommand() {
        super("save_json", "Save entities to json-file", true);
    }

    @Override
    public void execute() throws IOException, URISyntaxException {
        new DomainServiceImpl(getServiceLocator()).saveJSON();
    }
}
