package org.dragard.projectmanager.command;

import java.io.IOException;
import java.net.URISyntaxException;

public class DataXMLSaveCommand extends AbstractCommand {

    public DataXMLSaveCommand() {
        super("save_xml", "Save entities to xml-file", true);
    }

    @Override
    public void execute() throws IOException, URISyntaxException {
        getServiceLocator().getDomainService().saveXML();
    }
}
