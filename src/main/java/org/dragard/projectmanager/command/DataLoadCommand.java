package org.dragard.projectmanager.command;

import org.dragard.projectmanager.domain.DomainImpl;

import java.io.IOException;
import java.net.URISyntaxException;

public class DataLoadCommand extends AbstractCommand {

    public DataLoadCommand() {
        super("load", "Load entities from file", true);
    }

    @Override
    public void execute() {
        try {
            new DomainImpl(getServiceLocator()).load();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
