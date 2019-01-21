package org.dragard.projectmanager.command;

import java.io.IOException;
import java.net.URISyntaxException;

public class DataJSONSaveCommand extends AbstractCommand {

    public DataJSONSaveCommand() {
        super("save_json", "Save entities to json-file", true);
    }

    @Override
    public void execute() throws IOException, URISyntaxException {
        getServiceLocator().getDomainService().saveJSON();
    }
}
