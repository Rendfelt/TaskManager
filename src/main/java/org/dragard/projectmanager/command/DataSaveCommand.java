package org.dragard.projectmanager.command;

import org.dragard.projectmanager.domain.DomainImpl;

import java.io.IOException;
import java.net.URISyntaxException;

public class DataSaveCommand extends AbstractCommand {

    public DataSaveCommand() {
        super("save", "Save entities to file");
    }

    @Override
    public void execute() {
        try {
            new DomainImpl(getServiceLocator()).save();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
