package org.dragard.projectmanager.server.command;

import java.io.IOException;
import java.net.URISyntaxException;

public class DataXMLLoadCommand extends AbstractCommand {

    public DataXMLLoadCommand() {
        super("load_xml", "Load entities from xml-file", true);
    }

    @Override
    public void execute() throws IOException, URISyntaxException {
        getServiceLocator().getDomainService().loadXML();
    }
}
