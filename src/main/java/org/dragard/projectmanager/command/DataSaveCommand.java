package org.dragard.projectmanager.command;

import org.dragard.projectmanager.service.DomainServiceImpl;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;

public class DataSaveCommand extends AbstractCommand {

    public DataSaveCommand() {
        super("save", "Save entities to file", true);
    }

    @Override
    public void execute() {
        try {
            new DomainServiceImpl(getServiceLocator()).save();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
