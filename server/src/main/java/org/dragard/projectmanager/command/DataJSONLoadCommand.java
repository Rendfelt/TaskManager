package org.dragard.projectmanager.command;

import org.dragard.projectmanager.service.DomainServiceImpl;

import java.io.IOException;
import java.net.URISyntaxException;

public class DataJSONLoadCommand extends AbstractCommand {

    public DataJSONLoadCommand() {
        super("load_json", "Load entities from json-file", true);
    }

    @Override
    public void execute() throws IOException, URISyntaxException {
        getServiceLocator().getDomainService().loadJSON();
    }
}
