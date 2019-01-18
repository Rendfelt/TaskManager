package org.dragard.projectmanager.command;

import org.dragard.projectmanager.service.DomainServiceImpl;

import java.io.IOException;
import java.net.URISyntaxException;

public class DataXMLSaveCommand extends AbstractCommand {

    public DataXMLSaveCommand() {
        super("save_xml", "Save entities to xml-file", true);
    }

    @Override
    public void execute() throws IOException, URISyntaxException {
            new DomainServiceImpl(getServiceLocator()).saveXML();
    }
}
