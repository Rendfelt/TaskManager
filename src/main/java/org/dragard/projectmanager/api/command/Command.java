package org.dragard.projectmanager.api.command;

public interface Command {

    void execute();

    String getName();

    String getDescription();


}
